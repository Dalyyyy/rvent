package org.services;

import org.entities.Enterprise;

import java.sql.SQLException;
import java.util.List;

public interface IEnterpriseService<E> {
    void register(Enterprise enterprise, String confirmedPassword) throws SQLException;

    void updateEnterprise(Enterprise enterprise) throws SQLException;

    void deleteEnterprise(int id) throws SQLException;

    List<Enterprise> getAllEnterprise() throws SQLException;
}
