package com.example.linyudeng202406kaoshi;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.a202406kaoshi.R;

public class FirstFragment extends Fragment {

    TextView vt_db_id;
    EditText  et_db_ISBN, et_db_title, et_db_author, et_db_publish;
    Button btn_db_save, btn_db_find, btn_db_update, btn_db_delete,btn_db_clear,btn_db_clear_max;
//    long contrctId; //记录ID的值
    DataBaseManager dbMgr; //编写数据库操作的类

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbMgr = new DataBaseManager(getContext()); // 初始化数据库管理类

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        btn_db_save = view.findViewById(R.id.btn_db_save);
        btn_db_find = view.findViewById(R.id.btn_db_find);
        btn_db_update = view.findViewById(R.id.btn_db_update);
        btn_db_delete = view.findViewById(R.id.btn_db_delete);
        btn_db_clear = view.findViewById(R.id.btn_db_clear);
        btn_db_clear_max = view.findViewById(R.id.btn_db_clear_max);

        vt_db_id = view.findViewById(R.id.et_db_id);
        et_db_ISBN = view.findViewById(R.id.et_db_ISBN);
        et_db_title = view.findViewById(R.id.et_db_title);
        et_db_author = view.findViewById(R.id.et_db_author);
        et_db_publish = view.findViewById(R.id.et_db_publish);

        btn_db_save.setOnClickListener(v -> mySave());
        btn_db_find.setOnClickListener(v -> myFind());
        btn_db_update.setOnClickListener(v -> myUpdate());
        btn_db_delete.setOnClickListener(v -> myDelete());
        btn_db_clear.setOnClickListener(v -> myClear());
        btn_db_clear_max.setOnClickListener(v -> myClearMax());

        return view;
    }

    private boolean validateInputs() {
        if (et_db_ISBN.getText().toString().isEmpty() || et_db_title.getText().toString().isEmpty() || et_db_author.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "四个都是必填项，你给我（请）重新填写", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void mySave() {
        if (validateInputs()) {
            long id = dbMgr.addBook(
                    et_db_ISBN.getText().toString(),
                    et_db_title.getText().toString(),
                    et_db_author.getText().toString(),
                    et_db_publish.getText().toString()
            );
            vt_db_id.setText(String.valueOf(id));
            Toast.makeText(getContext(), "好书保存好了", Toast.LENGTH_SHORT).show();
        }
    }

    // 通用函数，用于设置EditText的值
    private void setEditTextValue(Cursor cursor, String columnName, EditText editText) {
        int columnIndex = cursor.getColumnIndex(columnName);
        if (columnIndex != -1) {
            editText.setText(cursor.getString(columnIndex));
        } else {
            Log.e("DatabaseError", "Column " + columnName + " does not exist in the cursor.");
        }
    }
    private void setTextViewValue(Cursor cursor, String columnName, TextView editText) {
        int columnIndex = cursor.getColumnIndex(columnName);
        if (columnIndex != -1) {
            editText.setText(cursor.getString(columnIndex));
        } else {
            Log.e("DatabaseError", "Column " + columnName + " does not exist in the cursor.");
        }
    }

    private void myFind() {
        String isbn = et_db_ISBN.getText().toString();
        if (isbn.isEmpty()) Toast.makeText(getContext(), "别偷懒，输入ISBN", Toast.LENGTH_SHORT).show();
        Cursor cursor = dbMgr.getBookByISBN(isbn);

        if (cursor.moveToFirst()) {
//            int columnIndex;
////          int columnIndex = cursor.getColumnIndex(DataBaseManager.ID_FIELD);
////            if (columnIndex != -1) {
////                // Column exists, safe to use
////                String value = cursor.getString(columnIndex);
////            } else {
////                // Handle the case where the column doesn't exist
////                Log.e("DatabaseError", "Column " + COLUMN_NAME + " does not exist in the cursor.");
////            }
//            columnIndex = cursor.getColumnIndex(DataBaseManager.ID_FIELD);
//            if(columnIndex != -1){
//                et_db_id.setText(cursor.getString(columnIndex));
//            }
//            else{
//                Log.e("DatabaseError", "Column " + DataBaseManager.ID_FIELD + " does not exist in the cursor.");
//            }
//            et_db_title.setText(cursor.getString(cursor.getColumnIndex(DataBaseManager.TITLE_FIELD)));
//            et_db_author.setText(cursor.getString(cursor.getColumnIndex(DataBaseManager.AUTHOR_FIELD)));
//            et_db_publish.setText(cursor.getString(cursor.getColumnIndex(DataBaseManager.PUBLISH_FIELD)));
                // 使用通用函数设置ID字段的值
            setTextViewValue(cursor, DataBaseManager.ID_FIELD, vt_db_id);

                // 使用通用函数设置Title字段的值
            setEditTextValue(cursor, DataBaseManager.TITLE_FIELD, et_db_title);

                // 使用通用函数设置Author字段的值
            setEditTextValue(cursor, DataBaseManager.AUTHOR_FIELD, et_db_author);

                // 使用通用函数设置Publish字段的值
            setEditTextValue(cursor, DataBaseManager.PUBLISH_FIELD, et_db_publish);

            Toast.makeText(getContext(), "鬼东东找到了", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "(〃￣ω￣〃）ゞ我没有找到的耶", Toast.LENGTH_SHORT).show();
        }
    }

    private void myUpdate() {
        if (validateInputs() && !vt_db_id.getText().toString().isEmpty()) {
            long id = Long.parseLong(vt_db_id.getText().toString());
            int rows = dbMgr.updateBook(
                    id,
                    et_db_ISBN.getText().toString(),
                    et_db_title.getText().toString(),
                    et_db_author.getText().toString(),
                    et_db_publish.getText().toString()
            );
            if (rows > 0) {
                Toast.makeText(getContext(), "更新好了ᐠ( ᐢ ᵕ ᐢ )ᐟ", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "更新失败了▄█▀█●", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "ID不能为空(╯‵皿′)╯︵┻━┻", Toast.LENGTH_SHORT).show();
        }
    }

    private void myDelete() {
        String idStr = vt_db_id.getText().toString();
        if (!idStr.isEmpty()) {
            long id = Long.parseLong(idStr);
            int rows = dbMgr.deleteBook(id);
            if (rows > 0) {
                Toast.makeText(getContext(), "删了📖∂（*´∀｀*）", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "删除失败了( =①ω①=)", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "ID不能为空哦(　ﾟ∀ﾟ) ﾉ♡", Toast.LENGTH_SHORT).show();
        }
    }

    private void myClear(){
        vt_db_id.setText("");
        et_db_ISBN.setText("");
        et_db_title.setText("");
        et_db_author.setText("");
        et_db_publish.setText("");
        Toast.makeText(getContext(), "༼(*꒪ั❥꒪ั*༽༽清除了", Toast.LENGTH_SHORT).show();
    }

    private void myClearMax() {
        new AlertDialog.Builder(getContext())
                .setTitle("确认删除？")
                .setMessage("删了，回忆都没有了")
                .setPositiveButton("继续删除它", (dialog, which) -> {

                    new AlertDialog.Builder(getContext())
                         .setTitle("真的真的真的删除吗？")
                            .setMessage("呜呜呜呜😭😭😭😭")
                            .setPositiveButton("继续删除", (dialog_1, which_1) -> {

                                new AlertDialog.Builder(getContext())
                                        .setTitle("我们没有结果了吗？")
                                        .setMessage("呜呜呜呜呜呜呜呜😭😭😭😭呜呜呜呜呜呜呜呜😭😭😭😭")
                                        .setPositiveButton("没有", (dialog_2, which_2) -> {

                                            Toast.makeText(getContext(), "(;´༎ຶД༎ຶ`)都没了", Toast.LENGTH_SHORT).show();
                                            dbMgr.onUpgrade(dbMgr.getWritableDatabase(), 1, 1);
                                            myClear();

                                        }).show();
//                                        .setNegativeButton("最后给你一次机会", (dialog_2, which_2) -> dialog.dismiss()).show();
//                                Toast.makeText(getContext(), "(;´༎ຶД༎ຶ`)都没了", Toast.LENGTH_SHORT).show();
//                                dbMgr.onUpgrade(dbMgr.getWritableDatabase(), 1, 1);
//                                myClear();

                            })
                            .setNegativeButton("确定不取消吗！", (dialog_1, which_1) -> dialog.dismiss()).show();

                })
                .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }
}
