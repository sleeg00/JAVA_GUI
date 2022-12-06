package main;



import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import Group.*;
import login.Login_Fail;
import login.Login_Success;
import sign.*;
import todo.*;
//ToDo 추가
import java.time.LocalDate; //현재 시간을 받아줌

import static Group.Group_Manage.label;
import static sign.Sign_Search.k;


public class CalendarSwing extends JFrame implements  ItemListener, ActionListener {
    public static Font fnt = new Font("굴림체", Font.BOLD, 20);
    public static Font title_fnt = new Font("굴림체", Font.BOLD, 40);

    //메인 pane
    public static JPanel main_topPane = new JPanel(new BorderLayout());

    //메인 pane에 login 팬
    public static JPanel login_pane = new JPanel(new BorderLayout());
    public static JTextField id = new JTextField(8);
    public static JPasswordField  password = new JPasswordField (8);
    public static JButton login_btn = new JButton("Login");
    public static JButton sign_btn = new JButton("회원가입");


    //메인상단 pane 오른쪽팬
    public static JPanel mainright_pane = new JPanel(new FlowLayout());
    JLabel curTime = new JLabel();

    //왼쪽 pane
    public static JPanel west_pane = new JPanel(new GridLayout(0, 1));

    //회원가입
    public static JTextField sign_id = new JTextField(5); //회원가입 id
    public static JPasswordField sign_pw = new JPasswordField(5);//회원가입 비번
    public static JPasswordField sign_pwcheck = new JPasswordField(5);//회원가입 비번체크
    public static JTextField sign_name = new JTextField(5);//회원가입 닉네임
    public static JButton sign_ok = new JButton("회원가입");//회원가입하기
    public static JButton sign_cancel = new JButton("취소");//회원가입취소
    public static JLabel sign_error = new JLabel(); //에러메세지 ex)이미 존재하는 아이디,비밀번호가 너무 긺

    //그룹보기pane
    public static JComboBox<String> group_combo = new JComboBox<String>(); //그룹콤보박스에 들어갈 combo

    public static JLabel group_name = new JLabel("학교"); //그룹이름
    public static JLabel group_code = new JLabel("inje"); //그룹코드
    public static JList<String> person_List = new JList<String>();//그룹원 리스트
    public static JButton group_manage = new JButton("그룹 관리");// 그룹관리페이지로(groupManage)
    public static JButton group_exit = new JButton("그룹 탈퇴");//그룹나가기 추후 구현필요
    public static Vector<String> group_v = new Vector<String>(); //그룹원리스트에 추가될 벡터


    //그룹관리pane

    public static JTextField join_name = new JTextField(5); //가입할 그룹이름
    public static JTextField join_code = new JTextField(5); //가입할 그룹 코드
    public static JButton groupJoin_btn = new JButton("그룹가입"); //그룹가입하기 기능구현

    public static JTextField create_name = new JTextField(5); // 생성할 그룹 이름
    public static JTextField create_code = new JTextField(5);//생성할 그룹 코드
    public static JButton groupCreate_btn = new JButton("그룹생성");//그룹생성하기 기능구현

    public static JTextField update_name = new JTextField(5);//변경할 그룹 이름-> 페ㅐ이지들어오면 현재 선택했던 이름이 들가야댐
    public static JTextField update_code = new JTextField(5);//변경할 그룹 코드->페ㅐ이지들어오면 현재 선택했던 코드이 들가야댐
    public static JButton groupUpdate_btn = new JButton("그룹변경");//그룹변경하기 기능 구현

    public static JButton groupBack_btn = new JButton("뒤로가기");//그룹뷰 페이지로 전환


    //할일목록 pane
    public static JPanel east_pane = new JPanel(new GridLayout(10, 1)); //오른쪽pane ->오늘할일,추가 등등 페이지

    public static JButton change_cancel = new JButton("취소"); //오늘할일 취소->보기페이지로 전환
    public static JButton change_complet = new JButton("완료");//오늘할일 저장->보기페이지로 전환
    public static JButton change_add = new JButton("추가");//오늘할일 추가->보기페이지로 전환
    public static JButton change = new JButton("수정");//오늘할일 수정페이지로 전환
    //ToDo 여기부터
    public static JTextField[] change_list = {null, null, null, null, null, null, null};//오늘할일 저장할 텍스트필드들
    public static Vector<String> todo_list = new Vector<String>(); //할 일을 저장해주는 벡터
    public static String selectGroup, selectYear, selectMonth = "", selectDay = ""; //버튼을 눌렀을 때 그룹 정보, 년, 월, 일 저장
    public static JLabel[] l = new JLabel[7];
    public static int changSize = 0;
    //ToDo 여기까지
    public static int todoAdd_count = 0; //추가버튼 누를 떄 몇개인지 카운트


    //아래쪽 pane
    public static JPanel south_pane = new JPanel(new BorderLayout());

    //ToDO 여기부터
    public static Vector<String> D_day = new Vector<String>(7);
    public static Vector<String> D_day_dummy = new Vector<String>(7);

    public static boolean loginCheck = false;
    public static boolean dayBtnClickCheck = false;
//ToDO 여기껴지 수정


    //캘린더 pane
    public static JPanel calPane = new JPanel(new BorderLayout());

    //캘린더상단
    public static JPanel cal_topPane = new JPanel();
    public static JButton prevBtn = new JButton("이전");//이전버튼
    public static JButton nextBtn = new JButton("다음");//다음버튼
    public static JComboBox<Integer> yearCombo = new JComboBox<Integer>();//년도 콤보박스 추가
    public static JComboBox<Integer> monthCombo = new JComboBox<Integer>();//월 콤보박스 추가
    public static JLabel yearLBl = new JLabel("년");//"년" 표시 라벨
    public static JLabel monthLBl = new JLabel("월");//"월" 표시 라벨

    //캘린더중앙
    public static JPanel centerPane = new JPanel(new BorderLayout());//가운데 패널 생성후 레이아웃 설정
    public static JPanel titlePane = new JPanel(new GridLayout(1, 7));//타이틀을 생성시킬 패널을 생성하고 레이아웃 설정
    public static String title[] = {"일", "월", "화", "수", "목", "금", "토"};
    public static JPanel dayPane = new JPanel(new GridLayout(0, 7));//날짜가 나오는 패널

    //달력관련 데이터
    public static Calendar date;//달력주입
    public static int year;
    public static int month; //년,월 주입

    Container c = getContentPane();

    //로그인 클래스
    Login_Success loginSuccess = new Login_Success();
    Login_Fail loginFail = new Login_Fail();

    //그룹클래스
    Group_Info group_Info = new Group_Info();
    Group_Login group_Login = new Group_Login();
    Group_Make group_Make = new Group_Make();
    Group_Manage group_Manage = new Group_Manage();
    Group_Update group_Update = new Group_Update();

    //사인클래스
    Set_Sign setSign = new Set_Sign();
    Sign_Id signId = new Sign_Id();

    Sign_Search signSearch = new Sign_Search();

    //todo클래스
    Todo_Get todoGet = new Todo_Get();
    Todo_Default todoDefault = new Todo_Default();
    Todo_Change todoChange = new Todo_Change();
    Todo_Set todoSet = new Todo_Set();

    public CalendarSwing() throws SQLException {
        super("공유캘린터 자바 팀프로젝트");


        c.setLayout(new BorderLayout());
        c.setBackground(Color.black); //블랙할지 다른색할지는 정해봐야할듯
        date = Calendar.getInstance();//현재의 날짜 시간 객체 생성 및 객체를 받아온다.
        year = date.get(Calendar.YEAR);//캘린더에서 년을 받아와서 미리생성해놓은 year에 주입
        month = date.get(Calendar.MONTH) + 1; //월을 받아와서 month에 대입

        sign_cancel.addActionListener(this); //회원가입 취소 버튼 이벤트
        sign_btn.addActionListener(this);//회원가입버튼 이벤트
        login_btn.addActionListener(this);//로그인버튼 이벤트
        group_manage.addActionListener(this);//그룹관리버튼이벤트
        logout.addActionListener(this);
        sign_ok.addActionListener(this);
        groupCreate_btn.addActionListener(this);
        groupUpdate_btn.addActionListener(this);
        groupJoin_btn.addActionListener(this);
        group_exit.addActionListener(this);
        //todo 콤버박스 이벤트 수정

        group_combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(group_combo.getItemCount()>0) {
                    selectGroup = group_combo.getSelectedItem().toString();
                    setDay();
                    setDayReset();
                    setD_day();
                    todoDefault.todo_default();
                }
                else {
                    setDefaultD_Day();
                }
                System.out.println("리스트갯수" + group_combo.getItemCount());
            }
        });//콤보박스 선택시 이름이랑 code 관련된 할일목록 버튼에 나타나게 기능 구현



        group_combo.addActionListener(this);

        groupBack_btn.addActionListener(this);//페이지전환 그룹설정->그룹보기로
        change.addActionListener(this);//페이지전환 할일보기->할일수정
        mouse m = new mouse();
        change_add.addMouseListener(m);//할일 추가 기능 구현
        change_complet.addMouseListener(m); //할일 저장 기능 구현
        change_cancel.addMouseListener(m); //할일 취소 기능 구현

        //메인 상단
        JLabel title = new JLabel("2022 자바2 팀프로젝트");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(title_fnt);
        title.setBackground(new Color(0X9DDBFE));
        setLayout(new BorderLayout(10, 10));
        main_topPane.setBackground(new Color(0X9DDBFE));
        main_topPane.add(BorderLayout.CENTER, title);

        //로그인 팬
        JLabel id_l = new JLabel("            id : ");
        JLabel pw_l = new JLabel("password : ");
        JPanel id_p = new JPanel(new FlowLayout());
        id_p.setBackground(new Color(0XF5F5F5));
        JPanel pw_p = new JPanel(new FlowLayout());
        pw_p.setBackground(new Color(0XF5F5F5));


        //id비번 라벨값과 텍스트필드 넣기
        id_p.add(id_l);
        id_p.add(id);
        pw_p.add(pw_l);
        pw_p.add(password);
        id.setBorder(null); //텍스트박스 테두리 없애기
        password.setBorder(null);

        login_pane.add(BorderLayout.NORTH, id_p); //아이디 비번텍스트필드 프레임
        login_pane.add(BorderLayout.CENTER, pw_p);

        JPanel login_btnpane = new JPanel(new FlowLayout()); //로그인과 회원가입 버튼 프레임
        login_btnpane.add(sign_btn);
        login_btnpane.add(login_btn);
        login_btnpane.setBackground(new Color(0XF5F5F5));

        login_pane.add(BorderLayout.SOUTH, login_btnpane);
        login_pane.setBackground(new Color(0XF5F5F5));
        login_pane.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 30));
        main_topPane.add(BorderLayout.WEST, login_pane);

    //TODO: 현재 시간 - 스레드
        //메인 오른쪽 팬 ->딱히 신경안써도되는 부분
        mainright_pane.setSize(100, 100);
        JLabel curTimeLabel = new JLabel();

        curTimeLabel.setVerticalAlignment(SwingConstants.CENTER);
        curTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        curTimeLabel.setFont(new Font("Gothic", Font.ITALIC, 30));
        curTimeLabel.setBorder(BorderFactory.createEmptyBorder(0, 60, 20, 5));


        curTimeThread th = new curTimeThread(curTimeLabel);
        th.start();

        mainright_pane.add(curTimeLabel);

        mainright_pane.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 50));
        mainright_pane.setBackground(new Color(0XF5F5F5));
        main_topPane.add(BorderLayout.EAST, mainright_pane);


        //왼쪽 pane(기본)
        //회원가입 레이아웃 기본 설정
        west_pane.setSize(100, 100);
        JLabel west_l = new JLabel();
        west_pane.add(west_l);
        west_l.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 220));
        west_pane.setBackground(new Color(0XD1F7FF));//new Color(0xA2E8DB) //이전 색
        c.add(BorderLayout.WEST, west_pane);

        //오른쪽pane
        for (int i = 0; i < 7; i++) {
            change_list[i] = new JTextField(10); //그냥 텍필 재정의해주는 반복문
        }
        todoDefault.todo_default();//오늘의 할일 프레임 호출
        east_pane.repaint();

        c.add(BorderLayout.EAST, east_pane);

        //아래쪽pane
        setDefaultD_Day(); //디데이 아래쪽에 호출

        c.add(BorderLayout.SOUTH, south_pane);

        c.add(BorderLayout.NORTH, main_topPane);//메인 상단에 pane추가
        c.add(BorderLayout.CENTER, calPane);//메인 중간에 cal추가

        calPane.add(BorderLayout.NORTH, cal_topPane); //상하좌우 가운데로 나뉘어서 레이아웃 설정,위쪽에 대입시키고 pane삽입

        //달력 상단
        cal_topPane.setBackground(new Color(0xD1F7FF)); //배경색 주입
        cal_topPane.add(prevBtn);
        prevBtn.setFont(fnt);
        cal_topPane.add(yearCombo);
        yearCombo.setFont(fnt);
        cal_topPane.add(yearLBl);
        yearLBl.setFont(fnt);
        cal_topPane.add(monthCombo);
        monthCombo.setFont(fnt);
        cal_topPane.add(monthLBl);
        monthLBl.setFont(fnt);
        cal_topPane.add(nextBtn);
        nextBtn.setFont(fnt);

        //현재 년,월 설정
        setYear();
        setMonth();

        //title호출
        setCalendarTitle();  //요일을 만들어놓은 메소드
        centerPane.add(BorderLayout.NORTH, titlePane); //센터패널의 위쪽에 타이틀을 넣는다(요일)
        calPane.add(BorderLayout.CENTER, centerPane);

        //날짜만들기
        centerPane.add(dayPane); //센터패널에 날짜패널 추가
        setDay();

        //--------기능이벤트 추가----------
        prevBtn.addActionListener(this);
        nextBtn.addActionListener(this);
        //년월이벤트 다시 등록
        yearCombo.addItemListener(this);
        monthCombo.addItemListener(this);

        //JFrame의 설정들
        setSize(1820, 980);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public static JButton logout = new JButton("뒤로 가기");
    public void setLogin() throws SQLException {
        login_pane.removeAll();
        //로그인 팬
        JLabel id_l = new JLabel("                id : ");
        JLabel pw_l=new JLabel("password : ");
        JPanel id_p = new JPanel(new FlowLayout());
        id_p.setBackground(new Color(0XF5F5F5));
        JPanel pw_p = new JPanel(new FlowLayout());
        pw_p.setBackground(new Color(0XF5F5F5));

        //id비번 라벨값과 텍스트필드 넣기
        id_p.add(id_l);
        id_p.add(id);
        pw_p.add(pw_l);
        pw_p.add(password);
        id.setBorder(null); //텍스트박스 테두리 없애기
        password.setBorder(null);

        login_pane.add(BorderLayout.NORTH,id_p); //아이디 비번텍스트필드 프레임
        login_pane.add(BorderLayout.CENTER,pw_p);

        JPanel login_btnpane = new JPanel(new FlowLayout()); //로그인과 회원가입 버튼 프레임
        login_btnpane.add(sign_btn);
        login_btnpane.add(login_btn);
        login_btnpane.setBackground(new Color(0XF5F5F5));

        login_pane.add(BorderLayout.SOUTH,login_btnpane);
        login_pane.setBackground(new Color(0XF5F5F5));
        login_pane.setBorder(BorderFactory.createEmptyBorder(15 , 50, 15 , 30));
        main_topPane.add(BorderLayout.WEST,login_pane);
        main_topPane.repaint();
    }
    public static void setlogin_start(){
        JLabel id_l = new JLabel("                id : ");
        JLabel pw_l=new JLabel("password : ");
        JPanel id_p = new JPanel(new FlowLayout());
        id_p.setBackground(new Color(0XF5F5F5));
        JPanel pw_p = new JPanel(new FlowLayout());
        pw_p.setBackground(new Color(0XF5F5F5));


        //id비번 라벨값과 텍스트필드 넣기
        id_p.add(id_l);
        id_p.add(id);
        pw_p.add(pw_l);
        pw_p.add(password);
        id.setBorder(null); //텍스트박스 테두리 없애기
        password.setBorder(null);

        login_pane.add(BorderLayout.NORTH,id_p); //아이디 비번텍스트필드 프레임
        login_pane.add(BorderLayout.CENTER,pw_p);

        JPanel login_btnpane = new JPanel(new FlowLayout()); //로그인과 회원가입 버튼 프레임
        login_btnpane.add(sign_btn);
        login_btnpane.add(login_btn);
        login_btnpane.setBackground(new Color(0XF5F5F5));

        login_pane.add(BorderLayout.SOUTH,login_btnpane);
        login_pane.setBackground(new Color(0XF5F5F5));
        login_pane.setBorder(BorderFactory.createEmptyBorder(15 , 50, 15 , 30));
        main_topPane.add(BorderLayout.WEST,login_pane);
    }
    public static void setWelcome3(){
        login_pane.removeAll();

        //로그인 팬
        JLabel id_l = new JLabel("                id : ");
        JLabel pw_l=new JLabel("password : ");
        JPanel id_p = new JPanel(new FlowLayout());
        id_p.setBackground(new Color(0XF5F5F5));
        JPanel pw_p = new JPanel(new FlowLayout());
        pw_p.setBackground(new Color(0XF5F5F5));

        //id비번 라벨값과 텍스트필드 넣기
        id_p.add(id_l);
        id_p.add(id);
        pw_p.add(pw_l);
        pw_p.add(password);
        id.setBorder(null); //텍스트박스 테두리 없애기
        password.setBorder(null);

        login_pane.add(BorderLayout.NORTH,id_p); //아이디 비번텍스트필드 프레임
        login_pane.add(BorderLayout.SOUTH,pw_p);

        JPanel login_btnpane = new JPanel(new FlowLayout()); //로그인과 회원가입 버튼 프레임
        login_btnpane.add(sign_btn);
        login_btnpane.add(login_btn);
        login_btnpane.setBackground(new Color(0XF5F5F5));

        login_pane.add(BorderLayout.SOUTH,login_btnpane);
        login_pane.setBackground(new Color(0XF5F5F5));
        login_pane.setBorder(BorderFactory.createEmptyBorder(15 , 50, 15 , 30));
        main_topPane.add(BorderLayout.WEST,login_pane);

        login_pane.add(BorderLayout.NORTH,id_l); //아이디 비번텍스트필드 프레임
        login_pane.add(BorderLayout.CENTER,pw_p);


        login_pane.add(BorderLayout.SOUTH,logout);
        login_pane.setBackground(new Color(0XF5F5F5));
        login_pane.setBorder(BorderFactory.createEmptyBorder(30 , 90, 30 , 75));
        main_topPane.add(BorderLayout.WEST,login_pane);
        main_topPane.repaint();
    }


    public static boolean[] check = new boolean[32]; //1부터 담고 최대 31일
    public void getMonthTodo() {
        selectYear = yearCombo.getSelectedItem().toString();
        selectMonth = monthCombo.getSelectedItem().toString();
    try {
        String sql = "SELECT * FROM post WHERE post_team = '" + selectGroup + "' AND post_month = '" + selectMonth + "' AND post_year = '"+selectYear+ "'ORDER BY post_year , post_month, post_day, idx ASC";
        //+"' AND post_month >= '" + selectMonth+"'"
        ResultSet Rs = stmt.executeQuery(sql);
        while (Rs.next()) {//디비에서
            int tmpDay = Integer.parseInt(Rs.getString("post_day"));
            check[tmpDay] = true;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}



    //날짜설정
    public void setDay(){

        //요일
        date.set(year,month-1,1); //date를 세팅하고 요일을 1로 설정
        int week = date.get(Calendar.DAY_OF_WEEK); //요일값을 받아와서 저장
        //마지막날
        int lastDay = date.getActualMaximum(Calendar.DATE); //날짜가 셋팅된 Calender의 가장 큰 값
        //나중에 프린트로 테스트해봐야 자세히 알듯
        //공백
        for(int s=1;s<week;s++){
            JLabel lbl = new JLabel(" "); //들여쓰기
            dayPane.add(lbl);
        }
        //todo
        getMonthTodo();
        //날씨추가
        for(int day=1; day<=lastDay; day++){
            JButton lbl;
            if(check[day]){
                lbl = new JButton("<html>" + String.valueOf(day)+ "<br>!!</html>" );//라벨선언해주는데 String.value 는 형변환
            }else{
                lbl = new JButton("<html>" + String.valueOf(day)+ "<br></html>" );//라벨선언해주는데 String.value 는 형변환
            }

            lbl.setFont(fnt);
            lbl.setHorizontalAlignment(SwingConstants.LEFT);
            lbl.setVerticalAlignment(SwingConstants.TOP);
            //출력하는 날짜에대한 요일
            date.set(Calendar.DATE, day); //19->1
            int w=date.get(Calendar.DAY_OF_WEEK);//요일
            if(w==1) lbl.setForeground(Color.red);//요일중 일요일은 빨강
            else if(w==7) lbl.setForeground(Color.blue);//토요일은 파랑
            lbl.setContentAreaFilled(false);
            lbl.setBorder(new LineBorder(Color.black,1,false));
            dayPane.setBackground(Color.white);
            lbl.addActionListener(new ActionListener() {
                //todo 선택된 그룹과 년월일을 저장
                @Override //
                public void actionPerformed(ActionEvent e) {
                    dayBtnClickCheck = true;

                    selectGroup = group_combo.getSelectedItem().toString();//그룹 콤버박스
                    selectYear = yearCombo.getSelectedItem().toString();
                    selectMonth = monthCombo.getSelectedItem().toString();

                    String tmpDay = lbl.getText().substring(7,8);
                    if(tmpDay.equals("<")){
                        selectDay =  lbl.getText().substring(6,7);
                    }else{
                        selectDay =  lbl.getText().substring(6,8);
                    }
                    try {
                        east_pane.removeAll();
                        todoSet.setTodo();
                        east_pane.repaint();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            dayPane.add(lbl);
        }
        for(int i=1; i < 32; i++){ //일정 여부 초기화
            check[i] = false;
        }
    }

    //요일 설정
    public void setCalendarTitle(){
        for(int i=0;i<title.length; i++){ //만들어준 배열의 수만큼 반복(요일배열)
            JLabel lbl = new JLabel(title[i], JLabel.CENTER);
            lbl.setFont(fnt);
            if(i==0)lbl.setForeground(Color.red);//일요일포폰트 빨간색
            else if(i==6)lbl.setForeground(Color.blue);//토요일폰트 파란색
            titlePane.add(lbl); //타이틀패널에 라벨추가
        }
    }

    //년도 설정
    public void setYear(){
        for(int i=year-50;i<year+20;i++){
            yearCombo.addItem(i); //년도콤보박스에 추가
        }
        yearCombo.setSelectedItem(year);//이벤트와 연동시켜주기위해 추가
    }

    //월 설정
    public void setMonth(){
        for(int i=1;i<=12;i++){
            monthCombo.addItem(i);
        }
        monthCombo.setSelectedItem(month);//이벤트와 연동시키기위해 추가
    }
    //기본 d_day or 로그아웃 d_day
    public void setDefaultD_Day(){
        JPanel D_dayTitle = new JPanel(new GridLayout(1,7));
        D_day_dummy.removeAllElements();
        D_day.removeAllElements();
        //Todo 현재 시간 저장
        LocalDate now = LocalDate.now();
        int curYear = now.getYear();
        int curMonth =now.getMonthValue();
        int curDay = now.getDayOfMonth();
        D_day_dummy.add(curYear + "년 "+curMonth+ "월 "+ curDay+"일");
        D_day.add("D-day");
        JLabel lbl = new JLabel(D_day.get(0), JLabel.CENTER);
        lbl.setFont(fnt);
        D_dayTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lbl.setForeground(Color.red);
        D_dayTitle.add(lbl);

        JPanel D_daycontent = new JPanel(new GridLayout(1,7));
        JLabel lbl2 = new JLabel(D_day_dummy.get(0),JLabel.CENTER);
        lbl2.setFont(fnt);
        D_daycontent.setBorder(BorderFactory.createEmptyBorder(15, 0, 20, 0));
        lbl2.setBackground(Color.white);
        D_daycontent.add(lbl2);

        JPanel D_daypane = new JPanel(new BorderLayout());
        D_daypane.add(BorderLayout.NORTH,D_dayTitle);

        D_daycontent.setBackground(Color.white);
        D_daypane.add(BorderLayout.CENTER,D_daycontent);
        south_pane.add(D_daypane);
        south_pane.revalidate();
        south_pane.repaint();
    }
    //아래쪽 D-daypane
    //ToDO 디데이 수정
    public void setD_day(){
        south_pane.removeAll();

        JPanel D_dayTitle = new JPanel(new GridLayout(1,7));
        D_dayTitle.setBackground(new Color(0X9DDBFE));

        //Todo 현재 시간 저장
        LocalDate now = LocalDate.now();
        int curYear = now.getYear();
        int curMonth =now.getMonthValue();
        int curDay = now.getDayOfMonth();

        boolean CheckTodo = false;
        //ToDo 디데이 저장

        //ToDo 디데이 저장하기
        //선택된 그룹마다 디데이 달라지게
        //쿼리를 최대 7개만 가져와서
        //일정이 지워지면 새로 계산
        D_day.clear();
        D_day_dummy.clear();

        try {
            String sql = "SELECT * FROM post WHERE post_team = '" +selectGroup +"' AND post_year >= '"+selectYear+"'"  + " ORDER BY post_year , post_month, post_day, idx ASC";
            //+"' AND post_month >= '" + selectMonth+"'"
            ResultSet Rs = stmt.executeQuery(sql);

            while(Rs.next()){

                String postTeam  = Rs.getString("post_team");

                int postYear = Integer.parseInt(Rs.getString("post_year"));
                int postMonth = Integer.parseInt(Rs.getString("post_month"));
                int postDay = Integer.parseInt(Rs.getString("post_day"));

                System.out.println(Rs.getString("post_day")+ "할일=====");

//                    if(post)
                if(postMonth <= curMonth && postDay < curDay) continue; // 현재 월에 날짜가 오늘보다 작다면


                Calendar cal = Calendar.getInstance();
                cal.set(postYear,  postMonth, postDay);

                long dDay = cal.getTimeInMillis(); // 1000분의 1초로 계산
                long nowDay = System.currentTimeMillis();
                long result = dDay - nowDay +1;// 남은 시간: ms : 1000분의 1초 단위

                long dayTemp = (result / 1000 / 60 / 60 / 24) -31; //디데이 날짜 기준 계산

                if(dayTemp == 0){
                    D_day.add("D-day");
                }else{
                    D_day.add("D-"+Long.toString(dayTemp)); //디데이 저장
                }

                D_day_dummy.add(Rs.getString("post_text")); //내용 저장

                CheckTodo = true;

                if(D_day.size() == 7)break; //7개가 저장이 된다면 있다면 종료
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(CheckTodo == false){
            setDefaultD_Day();
        }else{
            for(int i=0;i<D_day.size(); i++){
                JLabel lbl = new JLabel(D_day.get(i), JLabel.CENTER);
                lbl.setFont(fnt);
                D_dayTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                lbl.setForeground(Color.red);
                D_dayTitle.add(lbl);
            }

            JPanel D_daycontent = new JPanel(new GridLayout(1,7));
            for(int i=0;i<D_day.size();i++){
                JLabel lbl = new JLabel(D_day_dummy.get(i),JLabel.CENTER);
                lbl.setFont(fnt);
                D_daycontent.setBorder(BorderFactory.createEmptyBorder(15, 0, 20, 0));
                lbl.setBackground(Color.white);
                D_daycontent.add(lbl);
            }

            JPanel D_daypane = new JPanel(new BorderLayout());
            D_daypane.add(BorderLayout.NORTH,D_dayTitle);

            D_daycontent.setBackground(Color.white);
            D_daypane.add(BorderLayout.CENTER,D_daycontent);
            south_pane.add(D_daypane);
            south_pane.revalidate();
            south_pane.repaint();
            CheckTodo = false;
        }


    }

    //콤보박스 클릭이벤트
    @Override
    public void itemStateChanged(ItemEvent e) {//콤보박스가 변경되었을 때 발생
        year = (int)yearCombo.getSelectedItem();//콤보박스에서 선택된값을 저장
        month = (int)monthCombo.getSelectedItem();//콤보박스에서 선택된값을 저장

        dayPane.setVisible(false);
        dayPane.removeAll();//원래있던 날짜 지우기
        setDay();//날짜처리
        dayPane.setVisible(true);

        //패널을 안닫으면 화면이 지워지지않음
    }

    //버튼 이벤트
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == prevBtn){  //이전버튼을 눌렀을 때
            //이전 월을 눌렀을때
            prevMonth();
            setDay();
            setDayReset();//날을 초기화시키는 메소드
        }else if(obj == nextBtn){ //다음버튼을 눌렀을 때
            //다음월을 눌렀을 때
            nextMonth();
            setDay();
            setDayReset();
        }else if(obj == change){ //화면전환 할일보기->할일수정
            if(loginCheck && dayBtnClickCheck){ //로그인이 되어 있고 날짜 버튼을 한 번이라도 클릭을 해야 한 수정 가능
                todoAdd_count=0;
                todoChange.changeTodo();
            }

        }
        else if(obj == sign_btn){//화면전환 회원가입페이지로
            setSign.setSign();
            sign_error.setText("");
        }
        else if(obj == sign_ok){//회원가입하기/////
            try{
                 signId.id();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
        else if(obj == sign_cancel){//회원가입취소
            west_pane.removeAll();
            west_pane.setSize(100,100);
            JLabel west_l = new JLabel();
            west_pane.add(west_l);
            west_l.setBorder(BorderFactory.createEmptyBorder(0 , 30, 15 , 210));
            west_pane.setBackground(new Color(0xD1F7FF));
        }
        else if(obj == groupBack_btn){//화면전환 그룹설정->그룹보기
            try {
                group_Info.setgroupView();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            west_pane.repaint();
        }
        else if(obj == group_manage){//화면전환 그룹보기-> 그룹설정
            label.setText("");
            group_Manage.setgroupManage();
            west_pane.repaint();
        }
        else if(obj == login_btn) {//로그인하기
            String ok = null;
            try {
                ok = signSearch.search();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (ok.equals("로그인 불가")) {
                System.out.println("로그인 불가");
                loginFail.login_fail();
            } else {

                try {
                    loginSuccess.login_success();
                    loginCheck = true;
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {

                    group_Info.setgroupView();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
            id.setText("");
            password.setText("");
        }
        else if(obj==logout){

            west_pane.removeAll();
            west_pane.setSize(100,100);
            JLabel west_l = new JLabel();
            west_pane.add(west_l);
            west_l.setBorder(BorderFactory.createEmptyBorder(0 , 30, 15 , 210));
            west_pane.setBackground(new Color(0xD1F7FF));
            loginCheck = false;
            todoDefault.todo_default();
            east_pane.revalidate();
            setDefaultD_Day();
            try {
                setLogin();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


            id.setText("");
            password.setText("");
        }
        else if(obj==groupJoin_btn){

            String pr= null;
            try {
                pr = group_Login.group_login();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            label.setText(pr);
            west_pane.repaint();
            west_pane.revalidate();
            join_code.setText("");
            join_name.setText("");
        }
        else if(obj==groupCreate_btn){
            try {
                if(group_Make.group_make().equals("성공!")){
                    label.setText("그룹 생성 완료");
                    west_pane.repaint();
                    west_pane.revalidate();
                }
                else {
                    System.out.println("!");
                    label.setText("그룹 생성 실패 -중복 오류.");
                    west_pane.repaint();
                    west_pane.revalidate();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            create_code.setText("");
            create_name.setText("");
        }
        else if(obj == group_combo){
            int p = 0;
            String w;
            if(group_combo.getItemCount() != 0)
                group_name.setText(group_combo.getSelectedItem().toString());
            System.out.println(group_name.getText());
            String SelectSql = "SELECT * FROM member WHERE member_group = "+"'"+group_name.getText()+"'";
            System.out.println(SelectSql);
            ResultSet resultSet = null;
            try {
                resultSet = stmt.executeQuery(SelectSql);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ArrayList<MemberBean> list = new ArrayList<MemberBean>();
            System.out.println("start");
            while (true) {
                try {
                    if (!resultSet.next()){
                        break;
                    }
                } catch (SQLException ex) {
                    System.out.println("값이 없대");
                }
                MemberBean memberBean = new MemberBean();
                try {
                    memberBean.setGroup_code(resultSet.getInt("member_group_code"));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                w=Integer.toString(memberBean.getGroup_code());
                group_code.setText(w);

            }
            String SelectSql2 = "SELECT * FROM member WHERE member_group = "+"'"+group_name.getText()+"'";

            ResultSet resultSet1 = null;
            try {
                resultSet1 = stmt.executeQuery(SelectSql2);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            group_v.clear();
            ArrayList<MemberBean> list2 = new ArrayList<MemberBean>();
            while (true) {
                if(resultSet1==null)
                    break;
                try {
                    if (!resultSet1.next()){
                        break;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                MemberBean memberBean = new MemberBean();
                try {
                    memberBean.setName(resultSet1.getString("member_name"));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                CalendarSwing.group_v.add(memberBean.getName());//그룹원 리스트에 들어갈 테스트값 ->콤보박스 바뀌면 처리해야함
                System.out.println(memberBean.getName());
            }
            person_List.setListData(group_v);
           CalendarSwing.person_List.setVisibleRowCount(3);
            CalendarSwing.person_List.setFixedCellWidth(70);
           west_pane.repaint();
        }
        else if(obj==groupUpdate_btn){
            try {
                if(group_Update.group_update().equals("성공!")){
                    label.setText("그룹 변경 완료!");
                    west_pane.repaint();
                    west_pane.revalidate();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            update_code.setText("");
            update_name.setText("");
        }
        else if(obj==group_exit){
            String sql =" UPDATE member SET member_group = '" + "-" + "', "+
                        "member_group_code = 0 "+
                        "where member_name = '" + k + "' and member_group = '"+group_name.getText()+"'";
            try {
                stmt.executeUpdate(sql);
                sign_error.setText("그룹 탈퇴 완료!");
                group_Info.setgroupView();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }


    }

    class mouse extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            JButton obj = (JButton) e.getSource();
            if (obj == change_add){//그룹추가
                if(todoAdd_count+todo_list.size()<7){//벡터값 7개까지만 가능하도록
                    todoAdd_count++;//추가를 누르면 추가카운트수만큼 텍스트필드 추가댐
                    todoChange.changeTodo();
                }
            }
            // 완료버튼
            else if(obj == change_complet){
                //ToDo 누른 버튼에 해당하는 그룹, 날짜의 포스트 row 다 삭제
                String sql = " DELETE FROM post " + " WHERE post_day = " +selectDay+ " AND post_month = "+selectMonth
                        + " AND post_year = " + selectYear +" AND post_team = '" +selectGroup + "'";
                try {
                    stmt = (Statement) con.createStatement();
                    int res = stmt.executeUpdate(sql);
                    if(res > 0) {
                        System.out.println("삭제 성공");
                    }

                } catch (SQLException err) {
                    err.printStackTrace();
                }

                int curPostCnt = todoAdd_count+todo_list.size(); //현재 텍스트 필드의 개수
                for(int i=0; i < curPostCnt; i++){
                    if(change_list[i] != null && change_list[i].getText().equals("")) continue; //빈칸이면 그냥 건너 뛰기
                    else {
                        sql = "";
                        // 현재 시간
                        LocalTime now = LocalTime.now();
                        // 시, 분, 초 구하기
                        int hour = now.getHour();
                        int minute = now.getMinute();
                        int second = now.getSecond();
                        String curTime = "" + selectYear + selectMonth + selectDay + hour + minute + second;
                        //텍박 앞에 숫자 긁어와서 idx column에 쓰기

                        sql = " INSERT INTO post VALUE('" + selectYear + "','" + selectMonth + "','" + selectDay + "','" + change_list[i].getText() +
                                "','" + selectGroup + "','" + i + curTime + "')";

                        System.out.println(sql);

                        stmt = null;
                        try {
                            stmt = (Statement) con.createStatement();
                            int res = stmt.executeUpdate(sql);
                            if (res > 0) {
                                System.out.println("삽입 성공");
                            }

                        } catch (SQLException err) {
                            err.printStackTrace();
                        }
                    }

                    //텍박에 있는 텍스트 날짜 그룹 디비에 insert
                }
                //ToDo 수정이 완료되면 디데이 수정

                setDay();
                setDayReset();
                setD_day();
                try {
                    todoSet.setTodo();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if(obj == change_cancel){//그룹수정취소
                try {
                    todoSet.setTodo();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }


    private void setDayReset(){ //날짜 초기화 메소드
        //년월 이벤트 등록해제
        yearCombo.removeItemListener(this);
        monthCombo.removeItemListener(this); //이벤트 등록해제

        yearCombo.setSelectedItem(year);//콤보박스에서 선택되었던 값을 가져옴
        monthCombo.setSelectedItem(month);

        dayPane.setVisible(false);
        dayPane.removeAll();
        setDay();
        dayPane.setVisible(true);

        yearCombo.addItemListener(this);//이벤트 다시 등록
        monthCombo.addItemListener(this);
    }

    public void prevMonth(){ //이전 월버튼 클릭했을 때
        if(month==1){
            year--;
            month=12;
        }else {
            month--;
        }
    }

    public void nextMonth(){ //다음 월버튼을 클릭했을 때
        if(month==12){
            year++;
            month=1;
        }else {
            month++;
        }
    }
    //메인메소드
    public static java.sql.Connection con =null;
    public static String server = "localhost:3306"; // MySQL 서버 주소
    public static String database = "java"; // MySQL DATABASE 이름
    public static String user_name = "root"; //  MySQL 서버 아이디
    public static String password5 = "21474836"; // MySQL 서버 비밀번호
    public static java.sql.Statement stmt;
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        con = (Connection) DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password5);
        stmt = con.createStatement(); // Query 작업을 실행하기 위한 객체.

        new CalendarSwing();
    }
}