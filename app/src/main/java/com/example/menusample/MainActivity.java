package com.example.menusample;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView _lvMenu;
    private List<Map<String, Object>> _menuList;
    private static final String[] FROM = {"name", "price"};
    private static final int[] TO = {R.id.tvMenuNameRow, R.id.tvMenuPriceRow};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lvMenu), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        _lvMenu = findViewById(R.id.lvMenu);
        _menuList = createTeishokuList();
        SimpleAdapter adapter =
                new SimpleAdapter(MainActivity.this, _menuList, R.layout.row, FROM, TO);
        _lvMenu.setAdapter(adapter);
        _lvMenu.setOnItemClickListener(new ListItemClickListener());

        registerForContextMenu(_lvMenu);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options_menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean returnVal = true;
        int itemId = item.getItemId();
        if (itemId == R.id.menuListOptionTeishoku) {
            _menuList = createTeishokuList();
        } else if (itemId == R.id.menuListOptionCurry) {
            _menuList = createCurryList();
        } else {
            returnVal = super.onOptionsItemSelected(item);
        }

        SimpleAdapter adapter =
                new SimpleAdapter(MainActivity.this, _menuList, R.layout.row, FROM, TO);
        _lvMenu.setAdapter(adapter);
        return returnVal;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context_menu_list, menu);
        menu.setHeaderTitle(R.string.menu_list_context_header);

//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_context_menu_list, menu);
//
//        // カスタムビューをヘッダーとして追加
//        LayoutInflater layoutInflater = getLayoutInflater();
//        View headerView = layoutInflater.inflate(R.layout.context_menu_header, null);
//        menu.setHeaderView(headerView);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        boolean returnVal = true;
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int listPosition = info.position;
        Map<String, Object>menu = _menuList.get(listPosition);

        int itemId = item.getItemId();
        if (itemId == R.id.menuListContextDesc) {
            String desc = (String) menu.get("desc");
            Toast.makeText(MainActivity.this, desc, Toast.LENGTH_LONG).show();
        } else if (itemId == R.id.menuListContextOrder) {
            order(menu);
        } else {
            returnVal = super.onContextItemSelected(item);
        }
        return returnVal;
    }

    private List<Map<String, Object>> createTeishokuList() {
        List<Map<String, Object>> menuList = new ArrayList<>();

        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "から揚げ定食");
        menu.put("price", 800);
        menu.put("desc", "若鳥のから揚げにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ハンバーグ定食");
        menu.put("price", 850);
        menu.put("desc", "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "生姜焼き定食");
        menu.put("price", 850);
        menu.put("desc", "すりおろし生姜を使った生姜焼きにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ステーキ定食");
        menu.put("price", 1000);
        menu.put("desc", "国産牛のステーキにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "野菜炒め定食");
        menu.put("price", 750);
        menu.put("desc", "季節の野菜炒めにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "とんかつ定食");
        menu.put("price", 900);
        menu.put("desc", "ロースとんかつにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ミンチかつ定食");
        menu.put("price", 850);
        menu.put("desc", "手ごねミンチカツにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "チキンカツ定食");
        menu.put("price", 900);
        menu.put("desc", "ボリュームたっぷりチキンカツにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "コロッケ定食");
        menu.put("price", 850);
        menu.put("desc", "北海道ポテトコロッケにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "焼き魚定食");
        menu.put("price", 850);
        menu.put("desc", "鰆の塩焼きにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "焼肉定食");
        menu.put("price", 950);
        menu.put("desc", "特性たれの焼肉にサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        return menuList;

    }

    private List<Map<String, Object>> createCurryList() {
        List<Map<String, Object>> menuList = new ArrayList<>();
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "ビーフカレー");
        menu.put("price", 520);
        menu.put("desc", "特選のスパイスをきかせた国産ビーフ100%のカレーです。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ポークカレー");
        menu.put("price", 420);
        menu.put("desc", "特選のスパイスをきかせた国産ポーク100%のカレーです。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ハンバーグカレー");
        menu.put("price", 620);
        menu.put("desc", "特選スパイスをきかせたカレーに手ごねハンバーグをトッピングです。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "チーズカレー");
        menu.put("price", 560);
        menu.put("desc", "特選スパイスをきかせたカレーにとろけるチーズをトッピングです。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "カツカレー");
        menu.put("price", 760);
        menu.put("desc", "特選スパイスをきかせたカレーに国産ロースカツをトッピングです。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "ビーフカツカレー");
        menu.put("price", 880);
        menu.put("desc", "特選スパイスをきかせたカレーに国産ビーフカツをトッピングです。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name", "からあげカレー");
        menu.put("price", 540);
        menu.put("desc", "特選スパイスをきかせたカレーに若鳥のから揚げをトッピングです。");
        menuList.add(menu);


        return menuList;
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            @SuppressWarnings("unchecked")
            Map<String, Object> item = (Map<String, Object>) parent.getItemAtPosition(position);
            order(item);
        }
    }

    private void order(Map<String, Object> menu) {
        String menuName = (String) menu.get("name");
        Integer menuPrice = (Integer) menu.get("price");

        Intent intent = new Intent(MainActivity.this, MenuThanksActivity.class);
        intent.putExtra("menuName", menuName);
        intent.putExtra("menuPrice", menuPrice + "円");
        startActivity(intent);
    }
}