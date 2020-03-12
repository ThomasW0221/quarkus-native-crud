package io.twinterf.repository;

import io.quarkus.runtime.annotations.RegisterForReflection;
import io.twinterf.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@RegisterForReflection
public class EmployeeRepository {

    @Inject
    public EntityManager em;

    @Transactional
    public List<Employee> findAllEmployees() {
        Query findAllQuery = em.createQuery("SELECT E FROM Employee E", Employee.class);
        List<Employee> results = findAllQuery.getResultList();
        return results;
    }

    @Transactional
    public Employee findEmployeeById(Long id) {
        return em.find(Employee.class, id);
    }

    @Transactional
    public void addEmployee(Employee employee) {
        em.persist(employee);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        Query deleteById = em.createNativeQuery("delete from employee where employeeId = ?");
        deleteById.setParameter(1, id);
        deleteById.executeUpdate();
    }
}
