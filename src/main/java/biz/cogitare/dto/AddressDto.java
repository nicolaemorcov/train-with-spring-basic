package biz.cogitare.dto;

import biz.cogitare.controller.ValidationUtils;
import biz.cogitare.model.AddressModel;
import biz.cogitare.model.TrainModel;

public final class AddressDto {

    private final String id;
    private final String streetName;
    private final String streetNumber;
    private final String postCode;

    public AddressDto(String id, String streetName, String streetNumber, String postCode) {
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


    public AddressModel convertToAddressModel(){
        AddressModel addressModel = new AddressModel(
                this.id,
                this.streetName,
                this.streetNumber,
                this.postCode
        );
        return addressModel;
    }



}
