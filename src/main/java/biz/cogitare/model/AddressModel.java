package biz.cogitare.model;

import biz.cogitare.controller.ValidationUtils;
import biz.cogitare.dto.AddressDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
public final class AddressModel {

    @Id
    private final String id;
    private final String streetName;
    private final String streetNumber;
    private final String postCode;

    public AddressModel(String id, String streetName, String streetNumber, String postCode) {
        this.id = ValidationUtils.isNotNullAndNotEmpty(id, "address ID should not be empty");
        this.streetName = ValidationUtils.isNotNullAndNotEmpty(streetName, "street name should not be empty");
        this.streetNumber = ValidationUtils.isNotNullAndNotEmpty(streetName, "street number should not be empty");
        this.postCode = ValidationUtils.isNotNullAndNotEmpty(postCode, "postcode should not be empty");
    }

    public String getId() {
        return id;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }

    public AddressDto convertToAddressDto(){
        AddressDto addressDto = new AddressDto(
                this.id,
                this.streetName,
                this.streetNumber,
                this.postCode
        );
        return addressDto;
    }




}
