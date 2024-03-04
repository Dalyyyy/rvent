package org.services;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.entities.Role;
import org.entities.User;
import org.util.RventDB;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class UserService implements IUserService{
    private Connection connection;
    public UserService(){
        connection = RventDB.getInstance().getConnection;
    }
    public static final String ACCOUNT_SID = "AC27fc4784fff140f94b9652128479d820";
    public static final String AUTH_TOKEN = "d5b2e159063437f934b01adf18904739";
    public static final String TWILIO_PHONE_NUMBER = "+14243534264";
    SmsService smsService = new SmsService();
    @Override
    public String[] authenticateUser(String email, String password) throws SQLException {
        String[] userInfo = new String[2]; // Array to hold user information: [0] = name, [1] = password
        String sql = "SELECT name, password FROM user WHERE email = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String storedName = resultSet.getString("name");
                    String storedPassword = resultSet.getString("password");

                    userInfo[0] = storedName; // Store the retrieved name
                    userInfo[1] = storedPassword; // Store the retrieved password

                    return userInfo;
                }
            }
        }

        return null; // If user not found
    }

    @Override
    public void register(User user, String confirmedPassword) throws SQLException {
        if (!user.getPassword().equals(confirmedPassword)) {
            throw new IllegalArgumentException("Password and confirmed password do not match.");
        }

        if (emailExists(user.getEmail())) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        String sql = "INSERT INTO user (name, familyName, email, password, dateBirth, role,phoneNumber) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getFamilyName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, hashPassword(user.getPassword()));
            preparedStatement.setDate(5, java.sql.Date.valueOf(user.getDateBirth()));
            preparedStatement.setString(6, Role.CLIENT.name());
            preparedStatement.setInt(7,user.getPhoneNumber());

            preparedStatement.executeUpdate();
        }
    }





    @Override
    public void addUser(User user, Role role) throws SQLException {
        if (emailExists(user.getEmail())) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        String sql = "INSERT INTO user (name, familyName, email, password, dateBirth, role, phoneNumber) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getFamilyName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, hashPassword(user.getPassword()));
            preparedStatement.setDate(5, java.sql.Date.valueOf(user.getDateBirth()));
            preparedStatement.setString(6, role.name());
            preparedStatement.setInt(7, user.getPhoneNumber());

            preparedStatement.executeUpdate();

            // Assuming sendSMS is a method within the same class or accessible through an instance of SmsService
            // and the user's phone number is stored in a format without the country code
            String welcomeMessage = "Welcome to our service, Mr/s :" + user.getName() + "!"+
                    " \n This is your Email :"+user.getEmail()+
                    "\n And this is your password "+user.getPassword();
            smsService.sendSMS(String.valueOf(user.getPhoneNumber()), welcomeMessage);
        } catch (SQLException e) {
            // handle SQL exception
            throw e;
        } catch (Exception e) {
            // handle other exceptions, including those from sendSMS
            e.printStackTrace();
        }
    }


    @Override
    public void updateUser(User user) throws SQLException {



        String sql = "update user set name=? , familyName=? , password=? , phoneNumber=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getFamilyName());
        preparedStatement.setString(3, hashPassword(user.getPassword()));

        preparedStatement.setInt(4,user.getPhoneNumber());

        preparedStatement.setObject(5, user.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM user WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,id);
        preparedStatement.executeUpdate();
    }



    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        // Update SQL to only select needed columns
        String sql = "SELECT id, name, familyName, email, password, role, reservationNbr, phoneNumber FROM user";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String familyName = resultSet.getString("familyName");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                // Handle enumeration for role
                String roleStr = resultSet.getString("role");
                Role role = Role.valueOf(roleStr.toUpperCase());
                int reservationNum = resultSet.getInt("reservationNbr");
                int phoneNumber = resultSet.getInt("phoneNumber");

                User user = new User(id, name, familyName, email, password, role, reservationNum, phoneNumber);
                users.add(user);
            }
        }

        return users;
    }
    public List<String> getAllOrganizerNamesNotInTeam() throws SQLException {
        List<String> organizerNames = new ArrayList<>();
        // Adjusted SQL query to select only the name of users with the role 'Organizer' and no team
        String sql = "SELECT name FROM user WHERE role = 'ORGANIZER' AND team_id IS NULL";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                organizerNames.add(name);
            }
        }

        return organizerNames;
    }


    public int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
    public String hashPassword(String password) {
        try {
            // Use SHA-256 hashing algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean verifyPassword(String password, String storedPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }
            String hashedPassword = stringBuilder.toString();

            return hashedPassword.equals(storedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean emailExists(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM user WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }


    public int getUserIdFromName(String organizerName) throws SQLException {
        // Initialize userID to an invalid value to indicate not found
        int userId = -1;

        // SQL query to fetch the user ID based on the name
        // Note: This assumes the 'name' column uniquely identifies a user, which may not be the case.
        // You might need to adjust this to match your schema, for example, using email or a combination of fields.
        String sql = "SELECT id FROM user WHERE name = ? AND role = 'ORGANIZER'";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, organizerName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // If the user is found, retrieve their ID
                if (resultSet.next()) {
                    userId = resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to retrieve user ID for the organizer: " + organizerName, e);
        }

        return userId;
    }

    public User getUserById(int userId) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM user WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, userId);

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            user = new User(); // Assuming you have a default constructor
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setFamilyName(rs.getString("familyName"));
            user.setPassword(rs.getString("password")); // Consider security implications
            user.setPhoneNumber(rs.getInt("phoneNumber"));
        }
        return user;
    }
}
