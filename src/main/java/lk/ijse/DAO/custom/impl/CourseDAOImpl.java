package lk.ijse.DAO.custom.impl;

import lk.ijse.BO.custom.CourseBO;
import lk.ijse.DAO.custom.CourseDAO;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {


    @Override
    public boolean save(Course course) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(course);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean delete(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql =("delete from Course where programId = :p_id");
        Query query = session.createQuery(hql);
        query.setParameter("p_id",id);
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public Course search(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = ("from Course where programId= :p_id");
        Query query = session.createQuery(hql);
        query.setParameter("p_id",id);

        List<Course> courseList = query.list();
        Course course1 = null;
        for(Course course : courseList){
            course1 = new Course(course.getProgramId(), course.getProgramName(), course.getDuration(), course.getFee());
        }
        transaction.commit();
        session.close();

        return course1;

    }

    @Override
    public boolean update(Course course) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(course);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Course> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql =("from Course");
        Query query = session.createQuery(hql);
        ArrayList<Course> allCourses = (ArrayList<Course>) query.list();

        transaction.commit();
        session.close();
        return  allCourses;
    }
}
