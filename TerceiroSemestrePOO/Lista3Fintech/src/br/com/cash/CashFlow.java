package br.com.cash;

import br.com.user.User;

import java.util.Date;

public class CashFlow {
	public Input input;
	public Output output;
	private User createdBy;
	private Date createdAt;

	public CashFlow(User createdBy) {
		this.createdBy = createdBy;
		this.createdAt = new Date();
	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	public Output getOutput() {
		return output;
	}

	public void setOutput(Output output) {
		this.output = output;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
