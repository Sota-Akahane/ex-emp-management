package com.example.service;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 従業員関連機能の業務処理を行うサービス.
 *
 * @author sota.akahane
 */
@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 従業員情報を全件検索する.
     *
     * @return 全従業員情報
     */
    public List<Employee> showList() {
        List<Employee> employeeList
                = employeeRepository.findAll();
        return employeeList;
    }

    /**
     * 従業員情報を取得する.
     *
     * @param id 従業員ID
     * @return 従業員情報
     */
    public Employee showDetail(Integer id) {
        Employee employee
                = employeeRepository.findById(id);

        return employee;
    }
}
