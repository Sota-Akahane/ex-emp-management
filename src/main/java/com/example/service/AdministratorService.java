package com.example.service;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理者関連機能の業務処理を行うサービス.
 *
 * @author sota.akahane
 */
@Service
@Transactional
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    /**
     * 管理者情報を挿入する.
     *
     * @param administrator 管理者情報
     */
    public void insert(Administrator administrator) {
        administratorRepository.insert(administrator);
    }

    /**
     * ログイン処理をする.
     *
     * @param mailAddress 入力されたメールアドレス
     * @param password    入力されたパスワード
     * @return 管理者情報
     */
    public Administrator login(String mailAddress, String password) {
        Administrator administrator
                = administratorRepository.findByMailAddressAndPassword(mailAddress, password);
        return administrator;
    }
}
