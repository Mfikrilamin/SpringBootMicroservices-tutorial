package com.springbootmicroservices.Reportservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootmicroservices.Reportservice.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
