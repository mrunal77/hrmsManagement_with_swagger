package com.rainier.beans;

public class SalaryDetailsBean {
	private int id;
	private Integer userId;
	private Integer currencyId;
	private Integer salaryTypeId;
	private String salary;
	private String bankName;
	private String accountHolderName;
	private String accountHoldingSince;
	private Integer accountClassTypeId;
	private Integer bankAccountId;
	private String accountNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public Integer getSalaryTypeId() {
		return salaryTypeId;
	}

	public void setSalaryTypeId(Integer salaryTypeId) {
		this.salaryTypeId = salaryTypeId;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getAccountHoldingSince() {
		return accountHoldingSince;
	}

	public void setAccountHoldingSince(String accountHoldingSince) {
		this.accountHoldingSince = accountHoldingSince;
	}

	public Integer getAccountClassTypeId() {
		return accountClassTypeId;
	}

	public void setAccountClassTypeId(Integer accountClassTypeId) {
		this.accountClassTypeId = accountClassTypeId;
	}

	public Integer getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(Integer bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
