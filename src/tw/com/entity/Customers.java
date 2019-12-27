package tw.com.entity;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;

@Entity
@Table(name="customers")
@NamedQueries(
	{@NamedQuery(name="findAll",
				query="SELECT c FROM Customers c")
	})
public class Customers implements Serializable {
	@Id
	@Column(name="customerNumber")
	private Long id;
	@Column(name="customerName")
	private String customerName;
	private static final long serialVersionUID = 1L;

	public Customers() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
   
}
