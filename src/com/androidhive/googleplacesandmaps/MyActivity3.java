package com.androidhive.googleplacesandmaps;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import static android.widget.Toast.*;

/**
 * Created by User on 13-Jan-15.
 */
public class MyActivity3 extends Activity {

    public String first_name;
    public String age;
    public String height;
    public String weight;
    public String sax;


    EditText age_in;
    EditText weight_in;
    EditText height_in;
    EditText etName;
    Button calc_btn;
     RadioGroup radioSexGroup;
     RadioButton radioSexButton;
     DatabaseHelper dbHelper;
    //RadioButton radimale;
   // RadioButton radifemale;
     Spinner spnr_height;
     Spinner spnr_weight;
    //Spinner spnr_weather;
     Spinner spnr_work;
    public static final CharSequence s_height[]={"inch","cm"};
    public static final CharSequence s_weight[]={"kg","lb"};

    public static final CharSequence s_work[]={"little or no exercise","light exercise/sports 1-3 days/week","moderate exercise/sports 3-5 days/week","hard exercise/sports 6-7 days a week"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //radimale = (RadioButton)findViewById(R.id.radiomale);
       // radifemale  = (RadioButton)findViewById(R.id.radiofemale);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main3);
        etName = (EditText) findViewById(R.id.etName);
        dbHelper = new DatabaseHelper(this);
        first_name="";
        age="";
        height="";
        weight="";
        sax="";

        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        age_in=(EditText)findViewById(R.id.editText);
        //male= (TextView) findViewById(R.id.textView);
        weight_in= (EditText) findViewById(R.id.editText3);
        height_in= (EditText) findViewById(R.id.editText2);
        calc_btn= (Button) findViewById(R.id.button_calc);
         final Spinner spnr_height = (Spinner) findViewById(R.id.spinnerHeight);
         final Spinner spnr_weight = (Spinner) findViewById(R.id.spinnerWeight);

        final Spinner spnr_work = (Spinner) findViewById(R.id.spinnerWork);
        // Create the ArrayAdapter
        ArrayAdapter<CharSequence> arrayAdapter1 = new ArrayAdapter<CharSequence>(MyActivity3.this
                ,android.R.layout.simple_spinner_item,s_height);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the Adapter
        spnr_height.setAdapter(arrayAdapter1);
        //spnr_height.setOnItemSelectedListener(this);
        // Create the ArrayAdapter
        ArrayAdapter<CharSequence> arrayAdapter2 = new ArrayAdapter<CharSequence>(MyActivity3.this
                ,android.R.layout.simple_spinner_item, s_weight);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the Adapter
        spnr_weight.setAdapter(arrayAdapter2);
        //spnr_weight.setOnItemSelectedListener(this);
        // Create the ArrayAdapter
        ArrayAdapter<CharSequence> arrayAdapter3 = new ArrayAdapter<CharSequence>(MyActivity3.this
                ,android.R.layout.simple_spinner_item,s_work);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the Adapter
        spnr_work.setAdapter(arrayAdapter3);

        calc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //first_name = ((EditText)findViewById(R.id.nijer_name)).getText().toString();

                if((age_in.getText().length()==0)|| (weight_in.getText().length()==0) || (height_in.getText().length()==0)||(radioSexGroup.getCheckedRadioButtonId()==-1)){

                                    Toast.makeText(getApplicationContext(), "Please give all the required inputs!!! =)",
               Toast.LENGTH_LONG).show();
                }

                else{
                    String ageinput= age_in.getText().toString();
                    String weightinput= weight_in.getText().toString();
                    String heightinput= height_in.getText().toString();
                    int Fixed=1;
                    // get selected radio button from radioGroup
                    int selectedId = radioSexGroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    radioSexButton = (RadioButton) findViewById(selectedId);
                    if ("MALE".equals(radioSexButton.getText())) {
                        Fixed = 66;
//                    Toast.makeText(getApplicationContext(), "this is my Toast message!!! =)",
//                            Toast.LENGTH_LONG).show();
                    } else if ("FE-MALE".equals(radioSexButton.getText())) {
                        Fixed = 655;
//                    Toast.makeText(getApplicationContext(), "this is other Toast message!!! =)",
//                            Toast.LENGTH_LONG).show();
                    }


                    // Intent notun=new Intent(MyActivity3.this,result.class);
                    float age_int, weight_int, height_int, weight_n, height_bmr, water, water_c;
                    float weight_d = 0;
                    float bmi = 1;
                    float bmr = 1;
                    int water_i = 1;
                    age_int = Float.parseFloat(ageinput);
                    weight_int = Float.parseFloat(weightinput);
                    height_int = Float.parseFloat(heightinput);
                    String kom = "Underwght";
                    String beshi = "Ovrwght";
                    String norm = "Normal";
                    String situ = "";
//                String Blnk="";


                    Intent notun = new Intent(MyActivity3.this, result.class);

                    if ("inch".equals(spnr_height.getSelectedItem()) && "kg".equals(spnr_weight.getSelectedItem())) {
                   // makeText(getApplicationContext(), "this is my Toast message!!! =)", LENGTH_LONG).show();
                        height_int *= 0.0254;
                        height_bmr = height_int;
                        height_bmr *= 100;
                        height_int = height_int * height_int;
                        bmi = weight_int / height_int;
                        bmr = Fixed + (float) (13.7 * weight_int) + (5 * height_bmr) - (float) (4.7 * age_int);
                        water = (float) (weight_int * 2.20462262);
                        water *= 2;
                        water /= 3;
                        water /= 8;
                        water_i = (int) water;
                        water_c = (float) (water_i + .5);
                        if (water >= water_c) {
                            water_i++;
                        }

                        if (bmi < 18.5) {
                            weight_n = (float) (18.5 * height_int);
                            weight_d = weight_n - weight_int;
                            if ("little or no exercise".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.2);
                            } else if ("light exercise/sports 1-3 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.375);
                            } else if ("moderate exercise/sports 3-5 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.55);
                            } else {
                                bmr = (float) (bmr * 1.9);
                            }

                            situ = kom; //male.setText("Your BMI number is "+bmi+".You are "+weight_d+"kg underweight.You need "+bmr+" Calories per day.\nIf you are sedentary (little or no exercise) you need "+bmr*1.2+" Calories.\nYou need "+water_i+" cups of water everyday.");
                        } else if (bmi >= 25) {
                            weight_n = (float) (25 * height_int);
                            weight_d = weight_int - weight_n;
                            if ("little or no exercise".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.2);
                            } else if ("light exercise/sports 1-3 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.375);
                            } else if ("moderate exercise/sports 3-5 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.55);
                            } else {
                                bmr = (float) (bmr * 1.9);
                            }
                            situ = beshi; //male.setText("Your BMI number is "+bmi+".You are "+weight_d+"kg overweight.You need "+bmr+"Calories per day.If you are sedentary (little or no exercise) you need "+bmr*1.2+" Calories.\nYou need "+water_i+" cups of water everyday.");
                        } else {
                            if ("little or no exercise".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.2);
                            } else if ("light exercise/sports 1-3 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.375);
                            } else if ("moderate exercise/sports 3-5 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.55);
                            } else {
                                bmr = (float) (bmr * 1.9);
                            }
                            situ = norm; //male.setText("Your BMI number is "+bmi+".You are normal.You need "+bmr+" Calories per day.\nIf you are sedentary (little or no exercise) you need "+bmr*1.2+" Calorie.\nYou need "+water_i+" cups of water everyday.");
                        }
                    } else if ("inch".equals(spnr_height.getSelectedItem()) && "lb".equals(spnr_weight.getSelectedItem())) {
                    /*Toast.makeText(getApplicationContext(), "this is my Toast message!!! =)",
                            Toast.LENGTH_LONG).show();*/
                        height_int *= 0.0254;
                        height_bmr = height_int;
                        height_bmr *= 100;
                        weight_int = (float) (weight_int * 0.453592);
                        height_int = height_int * height_int;
                        bmi = weight_int / height_int;

                        bmr = 66 + (float) (13.7 * weight_int) + (5 * height_bmr) - (float) (4.7 * age_int);
                        water = (float) (weight_int * 2.20462262);
                        water *= 2;
                        water /= 3;
                        water /= 8;
                        water_i = (int) water;
                        water_c = (float) (water_i + .5);
                        if (water >= water_c) {
                            water_i++;
                        }

                        if (bmi < 18.5) {
                            weight_n = (float) (18.5 * height_int);
                            weight_d = weight_n - weight_int;
                            if ("little or no exercise".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.2);
                            } else if ("light exercise/sports 1-3 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.375);
                            } else if ("moderate exercise/sports 3-5 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.55);
                            } else {
                                bmr = (float) (bmr * 1.9);
                            }

                            situ = kom; //male.setText("Your BMI number is "+bmi+".You are "+weight_d+"kg underweight.You need "+bmr+" Calories per day.\nIf you are sedentary (little or no exercise) you need "+bmr*1.2+" Calories.\nYou need "+water_i+" cups of water everyday.");
                        } else if (bmi >= 25) {
                            weight_n = (float) (25 * height_int);
                            weight_d = weight_int - weight_n;
                            if ("little or no exercise".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.2);
                            } else if ("light exercise/sports 1-3 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.375);
                            } else if ("moderate exercise/sports 3-5 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.55);
                            } else {
                                bmr = (float) (bmr * 1.9);
                            }
                            situ = beshi; //male.setText("Your BMI number is "+bmi+".You are "+weight_d+"kg overweight.You need "+bmr+"Calories per day.If you are sedentary (little or no exercise) you need "+bmr*1.2+" Calories.\nYou need "+water_i+" cups of water everyday.");
                        } else {
                            if ("little or no exercise".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.2);
                            } else if ("light exercise/sports 1-3 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.375);
                            } else if ("moderate exercise/sports 3-5 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.55);
                            } else {
                                bmr = (float) (bmr * 1.9);
                            }
                            situ = norm; //male.setText("Your BMI number is "+bmi+".You are normal.You need "+bmr+" Calories per day.\nIf you are sedentary (little or no exercise) you need "+bmr*1.2+" Calorie.\nYou need "+water_i+" cups of water everyday.");
                        }
                    } else if ("cm".equals(spnr_height.getSelectedItem()) && "kg".equals(spnr_weight.getSelectedItem())) {
                    /*Toast.makeText(getApplicationContext(), "this is my Toast message!!! =)",
                            Toast.LENGTH_LONG).show();*/
                        //height_int *= 0.0254;
                        height_bmr = height_int;
                        //height_bmr*=100;
                        height_int = height_int * height_int;
                        bmi = weight_int / height_int;
                        bmr = 66 + (float) (13.7 * weight_int) + (5 * height_bmr) - (float) (4.7 * age_int);
                        water = (float) (weight_int * 2.20462262);
                        water *= 2;
                        water /= 3;
                        water /= 8;
                        water_i = (int) water;
                        water_c = (float) (water_i + .5);
                        if (water >= water_c) {
                            water_i++;
                        }

                        if (bmi < 18.5) {
                            weight_n = (float) (18.5 * height_int);
                            weight_d = weight_n - weight_int;
                            if ("little or no exercise".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.2);
                            } else if ("light exercise/sports 1-3 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.375);
                            } else if ("moderate exercise/sports 3-5 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.55);
                            } else {
                                bmr = (float) (bmr * 1.9);
                            }

                            situ = kom; //male.setText("Your BMI number is "+bmi+".You are "+weight_d+"kg underweight.You need "+bmr+" Calories per day.\nIf you are sedentary (little or no exercise) you need "+bmr*1.2+" Calories.\nYou need "+water_i+" cups of water everyday.");
                        } else if (bmi >= 25) {
                            weight_n = (float) (25 * height_int);
                            weight_d = weight_int - weight_n;
                            if ("little or no exercise".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.2);
                            } else if ("light exercise/sports 1-3 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.375);
                            } else if ("moderate exercise/sports 3-5 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.55);
                            } else {
                                bmr = (float) (bmr * 1.9);
                            }
                            situ = beshi; //male.setText("Your BMI number is "+bmi+".You are "+weight_d+"kg overweight.You need "+bmr+"Calories per day.If you are sedentary (little or no exercise) you need "+bmr*1.2+" Calories.\nYou need "+water_i+" cups of water everyday.");
                        } else {
                            if ("little or no exercise".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.2);
                            } else if ("light exercise/sports 1-3 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.375);
                            } else if ("moderate exercise/sports 3-5 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.55);
                            } else {
                                bmr = (float) (bmr * 1.9);
                            }
                            situ = norm; //male.setText("Your BMI number is "+bmi+".You are normal.You need "+bmr+" Calories per day.\nIf you are sedentary (little or no exercise) you need "+bmr*1.2+" Calorie.\nYou need "+water_i+" cups of water everyday.");
                        }
                    } else if ("cm".equals(spnr_height.getSelectedItem()) && "lb".equals(spnr_weight.getSelectedItem())) {
                    /*Toast.makeText(getApplicationContext(), "this is my Toast message!!! =)",
                            Toast.LENGTH_LONG).show();*/
                        // height_int *= 0.0254;
                        height_bmr = height_int;
                        //height_bmr*=100;
                        weight_int = (float) (weight_int * 0.453592);
                        height_int = height_int * height_int;
                        bmi = weight_int / height_int;
                        bmr = 66 + (float) (13.7 * weight_int) + (5 * height_bmr) - (float) (4.7 * age_int);
                        water = (float) (weight_int * 2.20462262);
                        water *= 2;
                        water /= 3;
                        water /= 8;
                        water_i = (int) water;
                        water_c = (float) (water_i + .5);
                        if (water >= water_c) {
                            water_i++;
                        }

                        if (bmi < 18.5) {
                            weight_n = (float) (18.5 * height_int);
                            weight_d = weight_n - weight_int;
                            if ("little or no exercise".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.2);
                            } else if ("light exercise/sports 1-3 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.375);
                            } else if ("moderate exercise/sports 3-5 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.55);
                            } else {
                                bmr = (float) (bmr * 1.9);
                            }

                            situ = kom; //male.setText("Your BMI number is "+bmi+".You are "+weight_d+"kg underweight.You need "+bmr+" Calories per day.\nIf you are sedentary (little or no exercise) you need "+bmr*1.2+" Calories.\nYou need "+water_i+" cups of water everyday.");
                        } else if (bmi >= 25) {
                            weight_n = (float) (25 * height_int);
                            weight_d = weight_int - weight_n;
                            if ("little or no exercise".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.2);
                            } else if ("light exercise/sports 1-3 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.375);
                            } else if ("moderate exercise/sports 3-5 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.55);
                            } else {
                                bmr = (float) (bmr * 1.9);
                            }
                            situ = beshi; //male.setText("Your BMI number is "+bmi+".You are "+weight_d+"kg overweight.You need "+bmr+"Calories per day.If you are sedentary (little or no exercise) you need "+bmr*1.2+" Calories.\nYou need "+water_i+" cups of water everyday.");
                        } else {
                            if ("little or no exercise".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.2);
                            } else if ("light exercise/sports 1-3 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.375);
                            } else if ("moderate exercise/sports 3-5 days/week".equals(spnr_work.getSelectedItem())) {
                                bmr = (float) (bmr * 1.55);
                            } else {
                                bmr = (float) (bmr * 1.9);
                            }
                            situ = norm; //male.setText("Your BMI number is "+bmi+".You are normal.You need "+bmr+" Calories per day.\nIf you are sedentary (little or no exercise) you need "+bmr*1.2+" Calorie.\nYou need "+water_i+" cups of water everyday.");
                        }
                    }
 //database save                   
                    String name = etName.getText().toString();
            		String boyosh = age_in.getText().toString();
            		String lomba = height_in.getText().toString();
            		String ojon =weight_in .getText().toString();
            		String fuck = "";
            		if ("MALE".equals(radioSexButton.getText())) {
            			fuck = "MALE";
            		}
            		if ("FE-MALE".equals(radioSexButton.getText())) {
            			 fuck = "FE-MALE";
            		}
//            		Employee employee = new Employee(name, email, phone, designation);
//            		Toast.makeText(getApplicationContext(), employee.toString(),
//            				Toast.LENGTH_LONG).show();
            		long inserted=dbHelper.insertEmployee(name,boyosh,lomba,ojon,fuck);
            		if(inserted>=0)
            		{
            			Toast.makeText(getApplicationContext(), "New Profile Created", Toast.LENGTH_LONG).show();
            		}
            		else
            		{
            			Toast.makeText(getApplicationContext(), "No Insertion Done", Toast.LENGTH_LONG).show();
            		}

                    notun.putExtra("Bemai", bmi);
                    notun.putExtra("Obstha", situ);
                    notun.putExtra("Total_calorie", bmr);
                    notun.putExtra("extra_wght", weight_d);
                    notun.putExtra("cup_water", water_i);

                    startActivity(notun);

                }
            }
        });
    }
    
	public void view(View v)
	{
//		ArrayList<Employee>employees=dbHelper.getAllEmployees();
//
//		if(employees!=null && employees.size()>0)
//
//		{
//			for(Employee employee:employees)
//			{
//
//			Toast.makeText(getApplicationContext(),employee.toString(),Toast.LENGTH_LONG).show();
//			}
//		}
//		else if(employees.size()==0)
//		{
//			Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
//		}

		String name = etName.getText().toString();
		//email = ((EditText)findViewById(R.id.txtEmail)).getText().toString();
    String email;
	String 	phone;
		String designation;
		String number;
		String fuck;

		Cursor cursor = dbHelper.getUser(name);
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();
			name =cursor.getString(cursor.getColumnIndex("name"));

			email = cursor.getString(cursor.getColumnIndex("email"));

			phone = cursor.getString(cursor.getColumnIndex("phone"));

			designation = cursor.getString(cursor.getColumnIndex("designation"));

			fuck = cursor.getString(cursor.getColumnIndex("sex"));

			etName.setText(name);
			age_in.setText(email);
			height_in.setText(phone);
			weight_in.setText(designation);
			if("MALE".equals(fuck)){

				radioSexGroup.check(radioSexGroup.getChildAt(0).getId());
			}
			else{
				radioSexGroup.check(radioSexGroup.getChildAt(1).getId());
			}
			if (!cursor.isClosed()) {
				cursor.close();
			}
			//setUI();
		}
		Toast.makeText(getApplicationContext(), "Profile Info Retreived", Toast.LENGTH_LONG).show();
	}

 

}
