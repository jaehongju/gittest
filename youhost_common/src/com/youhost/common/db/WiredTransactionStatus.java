package com.youhost.common.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

/**
 * @author Lee HeeGu <elf_hazard@naver.com>,<elfHazard@gmail.com>
 *
 */
public class WiredTransactionStatus implements TransactionStatus {
	List<TransactionStatus> statusList = new ArrayList<TransactionStatus>();

	private boolean newTransaction;

	private boolean rollbackOnly;

	/* (non-Javadoc)
	 * @see org.springframework.transaction.TransactionStatus#hasSavepoint()
	 */
	public boolean hasSavepoint() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.springframework.transaction.TransactionStatus#isCompleted()
	 */
	public boolean isCompleted() {
		for (TransactionStatus status : statusList) {
			if (!status.isRollbackOnly()) {
				return false;
			}
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.transaction.TransactionStatus#isNewTransaction()
	 */
	public boolean isNewTransaction() {
		return newTransaction;
	}

	/* (non-Javadoc)
	 * @see org.springframework.transaction.TransactionStatus#isRollbackOnly()
	 */
	public boolean isRollbackOnly() {
		return (isLocalRollbackOnly() || isGlobalRollbackOnly());
	}

	/**
	 * Determine the rollback-only flag via checking this TransactionStatus.
	 * <p>Will only return "true" if the application called <code>setRollbackOnly</code>
	 * on this TransactionStatus object.
	 */
	public boolean isLocalRollbackOnly() {
		return this.rollbackOnly;
	}

	/**
	 * Template method for determining the global rollback-only flag of the
	 * underlying transaction, if any.
	 */
	public boolean isGlobalRollbackOnly() {
		for (TransactionStatus status : statusList) {
			if (status.isRollbackOnly()) {
				return true;
			}
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see org.springframework.transaction.TransactionStatus#setRollbackOnly()
	 */
	public void setRollbackOnly() {
		this.rollbackOnly = true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.transaction.SavepointManager#createSavepoint()
	 */
	public Object createSavepoint() throws TransactionException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.transaction.SavepointManager#releaseSavepoint(java.lang.Object)
	 */
	public void releaseSavepoint(Object savepoint) throws TransactionException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.springframework.transaction.SavepointManager#rollbackToSavepoint(java.lang.Object)
	 */
	public void rollbackToSavepoint(Object savepoint)
			throws TransactionException {
		// TODO Auto-generated method stub

	}

	/**
	 * 새로운 TransactionStatus를 추가합니다.
	 * 
	 * @param subStatus transaction status 정보
	 */
	public void addSubTransactionStatus(TransactionStatus subStatus) {
		statusList.add(0, subStatus);
	}

	/**
	 * 원하는 위치에 있는 transaction status 정보를 알려줍니다.
	 * 
	 * @param index status가 있는 위치. 0-base
	 * @return transaction status 정보
	 */
	public TransactionStatus getTransactionStatus(int index) {
		return statusList.get(index);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

}
