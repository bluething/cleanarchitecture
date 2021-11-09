package io.github.bluething.cleanarchitecture.hexagonalarchitecture.codekata.bank.infrastructure.persistence;

import io.github.bluething.cleanarchitecture.hexagonalarchitecture.codekata.bank.application.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class SpringDataAccountRepository extends JdbcDaoSupport {

    private DataSource dataSource;

    @Autowired
    public SpringDataAccountRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public Account findByAccountNo(Long accountNo) {
        String sql = "SELECT * FROM account WHERE accountNo = ?";
        return (Account) getJdbcTemplate().queryForObject(sql, new Object[] { accountNo }, new RowMapper<Account>() {
            public Account mapRow(ResultSet rs, int rwNumber) throws SQLException {
                Account account = new Account(rs.getLong("accountNo"), rs.getBigDecimal("balance"));
                return account;
            }
        });
    }

    public void save(Account bankAccount) {
        String sql = "UPDATE account SET " + "balance= ? WHERE accountNo = ?";
        getJdbcTemplate().update(sql, new Object[] { bankAccount.getAccountBalance(), bankAccount.getAccountNo() });
    }
}
