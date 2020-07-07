package org.example;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import com.owlike.genson.Genson;

@DataType()
public class Payments {

    private final static Genson genson = new Genson();

    @Property()
    private String value;

    public Payments(){
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

    public static Payments fromJSONString(String json) {
        Payments asset = genson.deserialize(json, Payments.class);
        return asset;
    }
}
