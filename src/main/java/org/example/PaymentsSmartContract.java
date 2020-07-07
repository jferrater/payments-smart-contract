package org.example;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import com.owlike.genson.Genson;

@DataType()
public class PaymentsSmartContract {

    private final static Genson genson = new Genson();

    @Property()
    private String value;

    public PaymentsSmartContract(){
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toJSONString() {
        return genson.serialize(this).toString();
    }

    public static PaymentsSmartContract fromJSONString(String json) {
        PaymentsSmartContract asset = genson.deserialize(json, PaymentsSmartContract.class);
        return asset;
    }
}
