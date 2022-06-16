package com.example.productmanage.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.productmanage.Adapter.ProductAdapter;
import com.example.productmanage.Adapter.ProductSpinnerAdapter;
import com.example.productmanage.Interface.OnClickInterface;
import com.example.productmanage.Interface.SendDataProductInterface;
import com.example.productmanage.Model.Products;
import com.example.productmanage.R;
import com.example.productmanage.UpdateProduct;

import java.util.ArrayList;
import java.util.List;

public class FragmentAddProduct extends Fragment {
    private Spinner spnImgProduct;
    private EditText edtNameProduct, edtDesProduct, edtPriceProduct;
    private Button btnAddProduct;
    private ProductAdapter productAdapter;
    private List<Products> productsList;
    private int imgs[] = {R.color.teal_200,
            R.color.teal_700,
            R.color.purple_200,
            R.color.purple_500,
            R.color.purple_700};
    private int pcurr;
    private SendDataProductInterface sendDataProductInterface;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        sendDataProductInterface = (SendDataProductInterface) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spnImgProduct = view.findViewById(R.id.spn_img_product);
        edtNameProduct = view.findViewById(R.id.edt_product_name);
        edtDesProduct = view.findViewById(R.id.edt_product_description);
        edtPriceProduct = view.findViewById(R.id.edt_product_price);
        btnAddProduct = view.findViewById(R.id.btn_add_product);

        productsList = new ArrayList<>();
        productsList = FragmentHome.productsList;
        productAdapter = new ProductAdapter(getContext(), productsList);

        ProductSpinnerAdapter productSpinnerAdapter = new ProductSpinnerAdapter(getContext());
        spnImgProduct.setAdapter(productSpinnerAdapter);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemImg = spnImgProduct.getSelectedItem().toString();
                int imgProduct = R.color.teal_200;
                String nameProduct = edtNameProduct.getText().toString();
                String desProduct = edtDesProduct.getText().toString();
                String priceString = edtPriceProduct.getText().toString();


                if(TextUtils.isEmpty(nameProduct) || TextUtils.isEmpty(desProduct) || TextUtils.isEmpty(priceString)){
                    Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.dialog_fill_all_editext);

                    Button btnOk = dialog.findViewById(R.id.btn_ok);
                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else {
                    float priceProduct = 0;
                    try {
                        imgProduct = imgs[Integer.parseInt(itemImg)];
                        priceProduct = Float.parseFloat(priceString);
                    }catch (NumberFormatException e){

                    }
                    //productAdapter.addProduct(new Products(1, imgProduct, nameProduct, desProduct, priceProduct));
                    sendDataProductInterface.sendData(new Products(1, imgProduct, nameProduct, desProduct, priceProduct));
                    edtNameProduct.setText("");
                    edtDesProduct.setText("");
                    edtPriceProduct.setText("");
                    spnImgProduct.setSelection(0);
                    Toast.makeText(getContext(), "Add new product successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
