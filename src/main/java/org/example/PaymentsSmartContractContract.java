package org.example;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;
import static java.nio.charset.StandardCharsets.UTF_8;

@Contract(name = "PaymentsSmartContractContract",
    info = @Info(title = "PaymentsSmartContract contract",
                description = "My Smart Contract",
                version = "0.0.1",
                license =
                        @License(name = "Apache-2.0",
                                url = ""),
                                contact =  @Contact(email = "payments-smart-contract@example.com",
                                                name = "payments-smart-contract",
                                                url = "http://payments-smart-contract.me")))
@Default
public class PaymentsSmartContractContract implements ContractInterface {
    public  PaymentsSmartContractContract() {

    }
    @Transaction()
    public boolean paymentsSmartContractExists(Context ctx, String paymentsSmartContractId) {
        byte[] buffer = ctx.getStub().getState(paymentsSmartContractId);
        return (buffer != null && buffer.length > 0);
    }

    @Transaction()
    public void createPaymentsSmartContract(Context ctx, String paymentsSmartContractId, String value) {
        boolean exists = paymentsSmartContractExists(ctx,paymentsSmartContractId);
        if (exists) {
            throw new RuntimeException("The asset "+paymentsSmartContractId+" already exists");
        }
        PaymentsSmartContract asset = new PaymentsSmartContract();
        asset.setValue(value);
        ctx.getStub().putState(paymentsSmartContractId, asset.toJSONString().getBytes(UTF_8));
    }

    @Transaction()
    public PaymentsSmartContract readPaymentsSmartContract(Context ctx, String paymentsSmartContractId) {
        boolean exists = paymentsSmartContractExists(ctx,paymentsSmartContractId);
        if (!exists) {
            throw new RuntimeException("The asset "+paymentsSmartContractId+" does not exist");
        }

        PaymentsSmartContract newAsset = PaymentsSmartContract.fromJSONString(new String(ctx.getStub().getState(paymentsSmartContractId),UTF_8));
        return newAsset;
    }

    @Transaction()
    public void updatePaymentsSmartContract(Context ctx, String paymentsSmartContractId, String newValue) {
        boolean exists = paymentsSmartContractExists(ctx,paymentsSmartContractId);
        if (!exists) {
            throw new RuntimeException("The asset "+paymentsSmartContractId+" does not exist");
        }
        PaymentsSmartContract asset = new PaymentsSmartContract();
        asset.setValue(newValue);

        ctx.getStub().putState(paymentsSmartContractId, asset.toJSONString().getBytes(UTF_8));
    }

    @Transaction()
    public void deletePaymentsSmartContract(Context ctx, String paymentsSmartContractId) {
        boolean exists = paymentsSmartContractExists(ctx,paymentsSmartContractId);
        if (!exists) {
            throw new RuntimeException("The asset "+paymentsSmartContractId+" does not exist");
        }
        ctx.getStub().delState(paymentsSmartContractId);
    }

}
