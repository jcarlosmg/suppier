package com.metalsa.supplier.pojo;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;

import lombok.Data;




public interface  IUserPojo {
	 String getVendorId();
	 String getVendorName();
	 String getSegment1();
	 String getAttribute2();
	 String getIdioma();
}
