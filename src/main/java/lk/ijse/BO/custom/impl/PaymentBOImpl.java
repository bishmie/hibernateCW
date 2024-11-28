package lk.ijse.BO.custom.impl;

import lk.ijse.BO.custom.PaymentBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.custom.PaymentDAO;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.entity.Payement;

import java.io.IOException;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);


    }

