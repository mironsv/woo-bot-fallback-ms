package io.axtrading.botfallback.services;

import io.axtrading.botfallback.dto.Failure;
import io.axtrading.botfallback.repositories.NewFailureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class FailureService {


    @Autowired
    private NewFailureRepository newFailureRepository;

    public Long add(Failure failure) {
        return newFailureRepository.save(failure);
    }

    public String getInfo(int id, String name) {
        if (id > 0) {
            try {
                return newFailureRepository.readTable(id, name);
            } catch (SQLException e) {
                e.printStackTrace();
                return "SQL Error! " + name;
            }
        }
        return "id=" + id + " is incorrect!";
    }

    public String delete(int id) {
        try {
            if (newFailureRepository.deleteRecordTable(id)) return "Failure with <b>id " + id + "</b> is deleted";
            else return "Failure with <b>id " + id + "</b> is NOT deleted";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error SQL !";
        }
    }

}