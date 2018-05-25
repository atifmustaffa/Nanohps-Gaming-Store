/**
 * 'Atif Mustaffa
 * 1429619
 * 14 Dec 2016
 * WP_Project
 *
 */
package main;

public class ShippingDetails {

	String shipping_id, user_id, fullname, address, postcode, city, province, phone_num;
	public ShippingDetails() {
	}
	public ShippingDetails(String shipping_id, String user_id, String fullname, String address, String postcode,
			String city, String province, String phone_num) {
		this.shipping_id = shipping_id;
		this.user_id = user_id;
		this.fullname = fullname;
		this.address = address;
		this.postcode = postcode;
		this.city = city;
		this.province = province;
		this.phone_num = phone_num;
	}
	public String getShipping_id() {
		return shipping_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public String getFullname() {
		return fullname;
	}
	public String getAddress() {
		return address;
	}
	public String getPostcode() {
		return postcode;
	}
	public String getCity() {
		return city;
	}
	public String getProvince() {
		return province;
	}
	public String getPhone_num() {
		return phone_num;
	}

}
