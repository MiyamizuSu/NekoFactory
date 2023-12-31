package DreamFatcory.View;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.html.HTMLTableCaptionElement;

import DreamFactory.Exception.MultipleSelectException;
import DreamFactory.Exception.NomalException;
import DreamFactory.Exception.NotFoundException;
import DreamFactory.Exception.NothingContainException;
import DreamFactory.Exception.SameCodeException;
import DreamFactory.Exception.SomethingNullException;
import DreamFactory.Exception.WasQuotedException;
import DreamFatcory.Control.*;

import DreamFatcory.Control.AdminstratorController;
import DreamFatcory.Control.Data_operations;
import Main.*;
import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
public class GUI {
	private AdminstratorController adController=AdminstratorController.getInstance();
	private UserController uController=UserController.getInstance();
	private String filename="Database.dat";
	public GUI() {
		
	}
	//自定义标签设置
	public void setXY(Node node,double x,double y) {
		node.setLayoutX(x);
		node.setLayoutY(y);
	}
	//自定义按钮设置
	public void setXYWH(Button button,double x,double y,double w,double h) {
		button.setLayoutX(x);
		button.setLayoutY(y);
		button.setPrefWidth(w);
		button.setPrefHeight(h);
	}
	//
	public  void setWH(ImageView view,double w,double h) {
		view.setFitWidth(w);
		view.setFitHeight(h);
	}
	public  void setWH1(ImageView view,double x,double y,double w,double h) {
		this.setXY(view, x, y);
		view.setFitWidth(w);
		view.setFitHeight(h);
	}
	//第一视图的实现
	public void StartView() {
//		Database.dataBaseZ();
		Stage firstStage=new Stage();
		firstStage.setTitle("登陆界面");
		Pane root=new Pane();
		Label label=new Label();
		Button button1=new Button();
		Button button2=new Button();
		label.setStyle("-fx-text-fill: RGB(218,120,214);"+"-fx-font-weight: bold;");
		button1.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		button2.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		TextField text1=new TextField();
		TextField text2=new TextField();
//		Image background = new Image("file:images/头图.jpg");
//		BackgroundImage backIm=new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,new BackgroundSize(800, 600, false, false, true, true));
        Image backgroundImage = new Image("file:images/10.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setOpacity(0.7);
        this.setWH(backgroundImageView, 1000,1000);
        root.getChildren().add(backgroundImageView);
		text1.setPromptText("账号:");
		text2.setPromptText("密码:");
		button1.setText("登录");
		button2.setText("注册");
		button1.setOnAction(event -> {
			try {
			String name=text1.getText();
			String key=text2.getText();
			if(adController.Log_in(name, key, 1,filename))
			{
				this.displayHappyEnd("登陆成功");
				PauseTransition pause=new PauseTransition(Duration.seconds(1));
				pause.setOnFinished(event1 ->{
					firstStage.close();
				});
				pause.play();
			}
			else if(uController.Log_in(name, key,filename))
			{
				this.displayHappyEnd2("登陆成功",name);
				PauseTransition pause=new PauseTransition(Duration.seconds(1));
				pause.setOnFinished(event1 ->{
					firstStage.close();
				});
				pause.play();
			}
			else {
			this.displayBadEnd("密码错误");
			}
		}
			catch(NomalException e) {
				this.ExceptionEnd("经销商功能还在开发中呢喵~");
			}
		}
		);
		button2.setOnAction(event ->{
			firstStage.close();
			this.displayTheSecondView();
		});
		label.setText("云平台制造");
		this.setXY(label, 300, 200);
		this.setXY(text2, 300, 600);
		this.setXY(text1, 300, 550);
		text1.setPrefWidth(300);
		text1.setPrefHeight(10);
		text2.setPrefWidth(300);
		text2.setPrefHeight(10);
		this.setXYWH(button2,200, 700,100,50);
		this.setXYWH(button1,600, 700,100,50);
		label.setFont(Font.font("Arial", 70));
//		root.setBackground(new Background(backIm));
		root.getChildren().add(label);
		root.getChildren().add(button1);
		root.getChildren().add(button2);
		root.getChildren().add(text2);
		root.getChildren().add(text1);
		Scene nowScene =new Scene(root,1000,1000);
		firstStage.setScene(nowScene);
		firstStage.show();
	}
	//管理员登录成功视图
	public void displayHappyEnd(String Text) {
		Stage happy=new Stage();
		Label well=new Label();
		well.setText(Text);
		StackPane root=new StackPane();
        Image backgroundImage = new Image("file:images/10.jpg");
		well.setStyle("-fx-text-fill: RGB(218,120,214);"+"-fx-font-weight: bold;");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setOpacity(0.7);
        this.setWH(backgroundImageView, 200,200);
        root.getChildren().add(backgroundImageView);
		root.getChildren().add(well);
		Scene gooodScene =new Scene(root,200,200);
		happy.setScene(gooodScene);
		PauseTransition pause = new PauseTransition(Duration.seconds(1));
		pause.setOnFinished(event -> {
		happy.close();
		this.displayTheThirdView();});
		pause.play();
		happy.show();
	}
	//用户登录成功视图
	public void displayHappyEnd2(String Text,String name) {
		Stage happy=new Stage();
		Label well=new Label();
		well.setText(Text);
		StackPane root=new StackPane();
		root.getChildren().add(well);
        Image backgroundImage = new Image("file:images/10.jpg");
		well.setStyle("-fx-text-fill: RGB(218,120,214);"+"-fx-font-weight: bold;");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setOpacity(0.7);
        this.setWH(backgroundImageView, 200,200);
        root.getChildren().add(backgroundImageView);
		Scene gooodScene =new Scene(root,200,200);
		happy.setScene(gooodScene);
		PauseTransition pause = new PauseTransition(Duration.seconds(1));
		pause.setOnFinished(event -> {
		happy.close();
		this.displayTheNinthView(name);});
		pause.play();
		happy.show();
	}
	//登陆失败视图
	public void displayBadEnd(String Text) {
		Stage happy=new Stage();
		Label well=new Label();
		well.setText(Text);
		StackPane root=new StackPane();
		root.getChildren().add(well);
		well.setStyle("-fx-text-fill: RGB(218,120,214);"+"-fx-font-weight: bold;");
        Image backgroundImage = new Image("file:images/10.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setOpacity(0.7);
        this.setWH(backgroundImageView, 200,200);
        root.getChildren().add(backgroundImageView);
		Scene gooodScene =new Scene(root,200,200);
		happy.setScene(gooodScene);
		PauseTransition pause = new PauseTransition(Duration.seconds(1));
		pause.setOnFinished(event -> {
		happy.close();});
		pause.play();
		happy.show();
	}
	//注册成功视图
	public void displayHappyEnding(String Text) {
		Stage happy=new Stage();
		Label well=new Label();
		well.setText(Text);
		StackPane root=new StackPane();
		root.getChildren().add(well);
		well.setStyle("-fx-text-fill: RGB(218,120,214);"+"-fx-font-weight: bold;");
        Image backgroundImage = new Image("file:images/10.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setOpacity(0.7);
        this.setWH(backgroundImageView, 200,200);
        root.getChildren().add(backgroundImageView);
		Scene goodScene =new Scene(root,200,200);
		happy.setScene(goodScene);
		PauseTransition pause = new PauseTransition(Duration.seconds(1));
		pause.setOnFinished(event -> {
		happy.close();
		this.StartView();});
		pause.play();
		happy.show();
	}
	//第二视图的实现
	public void displayTheSecondView() {		
		int n=96;//行间距
		int mid=20;//初始y坐标
		int i=150;//text初始x
		int k= 300;//text长度
		Stage sceondStage =new Stage();
		Pane root=new Pane();
        Image backgroundImage = new Image("file:images/44.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setOpacity(0.35);
        this.setWH(backgroundImageView, 500,700);
        root.getChildren().add(backgroundImageView);
		Button button1=new Button("确定");Button button2=new Button("返回");
		button1.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		button2.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		Label la1=new Label();Label la2=new Label();Label la3=new Label();
		Label la4=new Label();Label la5=new Label();Label la6=new Label();Label la7=new Label();
		la1.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		la2.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		la3.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		la4.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		la5.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		la6.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		la7.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		TextField text1=new TextField();TextField text2=new TextField();TextField text3=new TextField();
		TextField text4=new TextField();TextField text5=new TextField();TextField text6=new TextField();
		ToggleGroup raButtons =new ToggleGroup();
		RadioButton raButton1=new RadioButton("云工厂"); RadioButton raButton2=new RadioButton("经销商");
		raButton1.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		raButton2.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");

//		 String backgroundImageUrl ="file:///C://Users//80510//Desktop//顶级//106871956_p0.jpg";
//		 String css ="-fx-background-image: url('" + backgroundImageUrl + "');"+"-fx-background-size: cover;";
//	 	    root.setStyle(css);
		sceondStage.setTitle("注册界面");
		raButtons.selectedToggleProperty().addListener((observable, oldValue, newValue)->{
			button1.setOnAction(event -> {			
			String code=text1.getText();
			String key=text2.getText();
			String owenerName=text3.getText();
			String ownerTel =text4.getText();
				String OwnerZ="";
				if(newValue==raButton1)
				{
					OwnerZ ="云工厂";
				}
				if(newValue==raButton2) {
					OwnerZ="经销商";
				}
				String name=text5.getText();
				String des=text6.getText();
			try {
            uController.registered(filename,code,key, owenerName, ownerTel,OwnerZ,name,des);	
            this.displayHappyEnding("注册成功");
			PauseTransition pause = new PauseTransition(Duration.seconds(3));
			pause.setOnFinished(event1 ->{
			sceondStage.close();
			});
			pause.play();
			}	
				catch(SomethingNullException e)
				{
				this.ExceptionEnd(e);}
			catch(SameCodeException e) {
				this.ExceptionEnd(e);
			}
			});
			});
		button2.setOnAction(event1 ->{
			sceondStage.close();
			this.StartView();
		});
		this.setXY(la1,20,mid);this.setXY(la2,20,mid+n);this.setXY(la3, 20, mid+n*2);this.setXY(la4, 20, mid+n*3);
		this.setXY(la5, 20, mid+n*4);this.setXY(la6, 20, mid+n*5);this.setXY(la7, 20, mid+n*6);
		this.setXY(text1, i, mid);this.setXY(text2,i,mid+n);this.setXY(text3,i,mid+n*2);this.setXY(text4,i,mid+n*3);
		this.setXY(text5,i,mid+n*5);this.setXY(text6,i,mid+n*6);
		this.setXY(raButton1, i, mid+n*4);this.setXY(raButton2, i+200, mid+n*4);
		this.setXYWH(button1, 235, 630, 75,50);this.setXYWH(button2, 400, 630, 75,50);
		text1.setPrefWidth(k);text2.setPrefWidth(k);text3.setPrefWidth(k);text4.setPrefWidth(k);
		text4.setPrefWidth(k);text5.setPrefWidth(k);text6.setPrefWidth(k);
		la1.setText("登陆账号");la2.setText("登陆密码");la3.setText("真实姓名");la4.setText("联系方式");
		la5.setText("注册方式");la6.setText("工厂名称");la7.setText("工厂简介");
		la1.setFont(Font.font("Arial",20));la2.setFont(Font.font("Arial",20));la3.setFont(Font.font("Arial",20));
		la4.setFont(Font.font("Arial",20));la5.setFont(Font.font("Arial",20));la6.setFont(Font.font("Arial",20));
		la7.setFont(Font.font("Arial",20));
		root.getChildren().add(la1);root.getChildren().add(la2);root.getChildren().add(la3);root.getChildren().add(la4);
		root.getChildren().add(la5);root.getChildren().add(la6);root.getChildren().add(la7);
		root.getChildren().add(text1);root.getChildren().add(text2);root.getChildren().add(text3);root.getChildren().add(text4);
		root.getChildren().add(text5);root.getChildren().add(text6);
		raButton1.setToggleGroup(raButtons);raButton2.setToggleGroup(raButtons);
 	    root.getChildren().add(raButton1);root.getChildren().add(raButton2);
 	    root.getChildren().add(button1);root.getChildren().add(button2);

		Scene scene=new Scene(root,500,700);
		sceondStage.setScene(scene);
		sceondStage.show();
	}
	//第三视图的实现
	public void displayTheThirdView() {
		int n=300;//button长度
		Stage thirdStage=new Stage();
		thirdStage.setTitle("管理员界面");
	    VBox root=new VBox();
//	    Image image=new Image("C:\\Users\\80510\\Desktop\\顶级\\106871956_p0.jpg");
	    String backgroundImageUrl ="file:images/10.jpg";
	    String css = "-fx-background-color: rgba(255, 255, 255, 0.2); "+"-fx-background-image: url('" + backgroundImageUrl + "');"+"-fx-background-size: cover;";
//	    BackgroundImage backImage=new BackgroundImage(image,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,new BackgroundSize(500,500, false,false,true,false));
	    Button button1=new Button("用户管理");Button button2=new Button("云工厂管理");Button button3=new Button("产品类别管理");
	    Button button4=new Button("产品信息管理");Button button5=new Button("设备类型管理"); Button button6=new Button("设备管理");
		button1.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");button2.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");		
		button5.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");button3.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		button6.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");button4.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
//	    button1.setOpacity(0.9);button2.setOpacity(0.9);button3.setOpacity(0.9);s
//	    button4.setOpacity(0.9);button5.setOpacity(0.9);button6.setOpacity(0.9);
		root.setSpacing(80);
	    button1.setPrefWidth(n);button2.setPrefWidth(n);button3.setPrefWidth(n);
	    button4.setPrefWidth(n);button5.setPrefWidth(n);button6.setPrefWidth(n);
	    button1.setOnAction(event1->{
	    	thirdStage.close();
	    	displayTheForthView();
	    });
	    button2.setOnAction(event2->{
	    	thirdStage.close();
	    	this.displayTheFifthView();
	    });
	    button3.setOnAction(event3->{
	    	thirdStage.close();
	    	this.displaySixthView();
	    });
	    button4.setOnAction(event4->{
	    	thirdStage.close();
	    	this.displayTheSeventhView();
	    });
	    button5.setOnAction(event5->{
	    	thirdStage.close();
	    	this.ECatagoryView();
	    });;
	    button6.setOnAction(event6->{
	    	thirdStage.close();
	    	this.displayTheEighthView();
	    });
	    VBox.setMargin(button1, new Insets(50, 0, 0,0));
	    root.setAlignment(Pos.TOP_CENTER);
		root.getChildren().add(button1);root.getChildren().add(button2);root.getChildren().add(button3);
		root.getChildren().add(button4);root.getChildren().add(button5);root.getChildren().add(button6);
		Scene scene=new Scene(root,500,700);
//		Background back=new Background(backImage);
//		String css = "-fx-background-color: rgba(0, 0, 0, 0.5);";
		root.setStyle(css);
//		root.setBackground(back);
//		thirdStage.initStyle(StageStyle.TRANSPARENT);
		thirdStage.setScene(scene);
		thirdStage.show();
	}
	//第四视图的实现
	public void displayTheForthView() {
		int n=100;//button长度
		int k=25;//button宽度
		double i=139;//tableColumn长度
		Stage forthStage=new Stage();
		Pane root=new Pane();
		Image image=new Image("file:images/11.png");
		ImageView imView=new ImageView(image);
		Image image1=new Image("file:images/22.png");
		ImageView imView1=new ImageView(image1);
		TableView<User> tableView=new TableView<>();
		ObservableList<User> data=FXCollections.observableArrayList(adController.showAllUser(filename));
		TableColumn<User, Integer> IDColumn=new TableColumn<>("ID");
		TableColumn<User,String> codeColumn=new TableColumn<>("登陆账号");
		TableColumn<User,String> nameColumn=new TableColumn<>("姓名");
		TableColumn<User,String> telColumn=new TableColumn<>("联系方式");
		TableColumn<User,String> zColumn=new TableColumn<>("角色名称");
		IDColumn.setPrefWidth(i);codeColumn.setPrefWidth(i);nameColumn.setPrefWidth(i);
		telColumn.setPrefWidth(i);zColumn.setPrefWidth(i);
		IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		telColumn.setCellValueFactory(new PropertyValueFactory<>("Tel"));
		zColumn.setCellValueFactory(new PropertyValueFactory<>("Z"));
		TextField textField=new TextField();
		textField.setPromptText("姓名:");
		forthStage.setTitle("用户管理");
		Button button1=new Button("查找");Button button2=new Button("重置"); Button button3=new Button("新建");
		Button button4=new Button("删除");Button button5=new Button("修改"); Button button6=new Button("返回");
		IDColumn.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		codeColumn.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		nameColumn.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		telColumn.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		zColumn.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		button1.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");button2.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button6.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");button3.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button5.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");button4.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		this.setXYWH(button1, 180,25 , n, k);this.setXYWH(button2, 300, 25, n, k);this.setXYWH(button6,700, 465, n, k);
		this.setXYWH(button3, 10, 125, n, k);this.setXYWH(button4, 130, 125, n, k);this.setXYWH(button5, 300, 125, n, k);
		this.setXY(textField, 10,25);
		this.setXY(tableView, 10, 150);tableView.setPrefSize(697, 300);
		this.setXY(imView, 600, 0);
		this.setXY(imView1,400, 0);
		imView.setFitHeight(150);
		imView.setFitWidth(200);
		imView1.setFitHeight(150);
		imView1.setFitWidth(200);
		button1.setOnAction(research->{
			String name =textField.getText();
			try {
			User ans=adController.GetUser(filename,name);
			ObservableList<User> dataZ=FXCollections.observableArrayList();
			dataZ.add(ans);
			textField.clear();
			tableView.setItems(dataZ);
			}
			catch(NotFoundException e) {
				this.ExceptionEnd(e);
			}
		});
		button2.setOnAction(reset->{
			ObservableList<User> dataZ=FXCollections.observableArrayList(adController.showAllUser(filename));
			tableView.setItems(dataZ);
		});
		button3.setOnAction(add->{
			this.displayNewView();
		});
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		button4.setOnAction(remove->{
			ObservableList<User> select=tableView.getSelectionModel().getSelectedItems();
			ArrayList<User> removions=new ArrayList<User>(select);
			try {
				adController.checkNullu(removions);
				this.checkView(removions);
			}
			catch(NothingContainException e) {
				this.ExceptionEnd(e);
			}
			
		});
		button5.setOnAction(change->{
			ObservableList<User> select=tableView.getSelectionModel().getSelectedItems();
			ArrayList<User> removions=new ArrayList<User>(select);
			try {
				adController.checkNullu(removions);
				adController.checkMultipleu(removions);
				this.changeView(removions);
			}
			catch(NothingContainException e) {
				this.ExceptionEnd(e);
			}
			catch(MultipleSelectException e) {
				this.ExceptionEnd(e);
			}
		});
		button6.setOnAction(action1 ->{
			forthStage.close();
			this.displayTheThirdView();
		});
		tableView.getColumns().add(IDColumn);tableView.getColumns().add(codeColumn);tableView.getColumns().add(nameColumn);
		tableView.getColumns().add(telColumn);tableView.getColumns().add(zColumn);
		root.getChildren().add(textField);
		root.getChildren().add(button1);root.getChildren().add(button2);root.getChildren().add(button3);
		root.getChildren().add(button4);root.getChildren().add(button5);root.getChildren().add(button6);
		root.getChildren().add(tableView);
		root.getChildren().add(imView);
		root.getChildren().add(imView1);
		tableView.setItems(data);
		root.setStyle("-fx-background-color: white;");
		Scene scene=new Scene(root,800,500);
//		forthStage.initStyle(StageStyle.TRANSPARENT);
		forthStage.setScene(scene);
		forthStage.show();
	}
	//确认是否删除界面
	public void checkView(ArrayList<User> removions) {
		Stage stage =new Stage();
		Pane root=new Pane();	
		Button button1=new Button("确认");
		Button button2=new Button("再想想");
		button1.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button2.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		Label label=new Label();
		label.setText("请再次确认是否要删除哦>.<");
		label.setStyle("-fx-text-fill: RGB(218,120,214);"+"-fx-font-weight: bold;");
		label.setFont(new Font(14));
		this.setXY(label, 10, 25);
		this.setXYWH(button2, 20, 55, 65, 25);
		this.setXYWH(button1, 105, 55, 65,25);

		button1.setOnAction(remove->{
			try {
			adController.removeUser(filename,removions);
			this.HappyEnd3("删除");
			}
			catch(NomalException e) {
				this.ExceptionEnd("这位用户还有未归还的设备喵~");
			}
			finally {
				stage.close();
			}
		});
		button2.setOnAction(back->{
			stage.close();
		});
		root.getChildren().add(button1);
		root.getChildren().add(button2);
		root.getChildren().add(label);
		Scene scene=new Scene(root,200,100);
		stage.setScene(scene);
		stage.show();
	}
	//成功界面
	public void HappyEnd3 (String contain) {
		Stage stage=new Stage();
		StackPane root=new StackPane();
		Label label=new Label();
		PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
		label.setFont(new Font(16));
        Image backgroundImage = new Image("file:images/10.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setOpacity(0.6);
        this.setWH(backgroundImageView, 200,100);
        root.getChildren().add(backgroundImageView);
		label.setText(contain+"成功啦,喵!");
		label.setStyle("-fx-text-fill: RGB(218,120,214);"+"-fx-font-weight: bold;");
		root.getChildren().add(label);
		Scene scene=new Scene(root,200,100);
		stage.setScene(scene);
		pause.setOnFinished(end->{
			stage.close();
		});
		stage.show();
		pause.play();
	}
	//与第四界面配套的新建界面
    public void displayNewView() {
		int n=96;//行间距
		int mid=20;//初始y坐标
		int i=150;//text初始x
		int k= 300;//text长度
		Stage sceondStage =new Stage();
		Pane root=new Pane();
		Button button1=new Button("确定");Button button2=new Button("返回");
		button1.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		button2.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		Label la1=new Label();Label la2=new Label();Label la3=new Label();
		Label la4=new Label();Label la5=new Label();Label la6=new Label();Label la7=new Label();
		TextField text1=new TextField();TextField text2=new TextField();TextField text3=new TextField();
		TextField text4=new TextField();TextField text5=new TextField();TextField text6=new TextField();
		la1.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		la2.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		la3.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		la4.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		la5.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		la6.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		la7.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		ToggleGroup raButtons =new ToggleGroup();
		RadioButton raButton1=new RadioButton("云工厂"); RadioButton raButton2=new RadioButton("经销商");
		raButton1.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		raButton2.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
        Image backgroundImage = new Image("file:images/44.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setOpacity(0.35);
        this.setWH(backgroundImageView, 500,700);
        root.getChildren().add(backgroundImageView);
		sceondStage.setTitle("内部注册界面");
		raButtons.selectedToggleProperty().addListener((observable, oldValue, newValue)->{
			String code=text1.getText();
			String key=text2.getText();
			String owenerName=text3.getText();
			String ownerTel =text4.getText();
			button1.setOnAction(event -> {
				String OwnerZ=null;
				if(newValue==raButton1)
				{
					OwnerZ ="云工厂";
				}
				if(newValue==raButton2) {
					OwnerZ="经销商";
				}
				String name=text5.getText();
				String des=text6.getText();
			try {
            uController.registered(filename, code,key, owenerName, ownerTel,OwnerZ,name,des);
            this.HappyEnd3("注册");
			PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
			pause.setOnFinished(event1 ->{
			sceondStage.close();
			});
			pause.play();
			}
			catch(SomethingNullException e) {
				this.ExceptionEnd(e);
			}
			catch(SameCodeException e) {
				this.ExceptionEnd("账号重复了喵~,换个账号名吧");
			}
			});
			});
		button2.setOnAction(event1 ->{
			sceondStage.close();
		});
		this.setXY(la1,20,mid);this.setXY(la2,20,mid+n);this.setXY(la3, 20, mid+n*2);this.setXY(la4, 20, mid+n*3);
		this.setXY(la5, 20, mid+n*4);this.setXY(la6, 20, mid+n*5);this.setXY(la7, 20, mid+n*6);
		this.setXY(text1, i, mid);this.setXY(text2,i,mid+n);this.setXY(text3,i,mid+n*2);this.setXY(text4,i,mid+n*3);
		this.setXY(text5,i,mid+n*5);this.setXY(text6,i,mid+n*6);
		this.setXY(raButton1, i, mid+n*4);this.setXY(raButton2, i+200, mid+n*4);
		this.setXYWH(button1, 235, 650, 75,50);this.setXYWH(button2, 400, 650, 75,50);
		text1.setPrefWidth(k);text2.setPrefWidth(k);text3.setPrefWidth(k);text4.setPrefWidth(k);
		text4.setPrefWidth(k);text5.setPrefWidth(k);text6.setPrefWidth(k);
		la1.setText("登陆账号");la2.setText("登陆密码");la3.setText("真实姓名");la4.setText("联系方式");
		la5.setText("注册方式");la6.setText("工厂名称");la7.setText("工厂简介");
		la1.setFont(Font.font("Arial",20));la2.setFont(Font.font("Arial",20));la3.setFont(Font.font("Arial",20));
		la4.setFont(Font.font("Arial",20));la5.setFont(Font.font("Arial",20));la6.setFont(Font.font("Arial",20));
		la7.setFont(Font.font("Arial",20));
		root.getChildren().add(la1);root.getChildren().add(la2);root.getChildren().add(la3);root.getChildren().add(la4);
		root.getChildren().add(la5);root.getChildren().add(la6);root.getChildren().add(la7);
		root.getChildren().add(text1);root.getChildren().add(text2);root.getChildren().add(text3);root.getChildren().add(text4);
		root.getChildren().add(text5);root.getChildren().add(text6);
		raButton1.setToggleGroup(raButtons);raButton2.setToggleGroup(raButtons);
 	    root.getChildren().add(raButton1);root.getChildren().add(raButton2);
 	    root.getChildren().add(button1);root.getChildren().add(button2);
		Scene scene=new Scene(root,500,700);
		sceondStage.setScene(scene);
		sceondStage.show();
    }
    //与第四界面配套的修改界面
    public void changeView(ArrayList<User>select) {
		int n=96;//行间距
		int mid=20;//初始y坐标
		int i=150;//text初始x
		int k= 300;//text长度
		Stage sceondStage =new Stage();
		Pane root=new Pane();
		User ans=select.get(0);
		Button button1=new Button("确定");Button button2=new Button("返回");
		button1.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		button2.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		Label la1=new Label();Label la2=new Label();Label la3=new Label();
		Label la4=new Label();Label la5=new Label();Label la6=new Label();Label la7=new Label();
		la1.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		la2.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		la3.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		la4.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		la5.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		la6.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		la7.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		TextField text1=new TextField(ans.getCode());TextField text2=new TextField(ans.getKey());TextField text3=new TextField(ans.getName());
		TextField text4=new TextField(ans.getTel());TextField text5=new TextField(ans.getFaname());TextField text6=new TextField(ans.getFaDes());
		RadioButton raButton1=new RadioButton("云工厂"); RadioButton raButton2=new RadioButton("经销商");
		raButton1.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		raButton2.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
        Image backgroundImage = new Image("file:images/44.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setOpacity(0.35);
        this.setWH(backgroundImageView, 500,700);
        root.getChildren().add(backgroundImageView);
		sceondStage.setTitle("用户修改界面");	
		if(ans.getZ().equals("云工厂"))
		{
			raButton1.setSelected(true);
		}
		if(ans.getZ().equals("经销商")) {
			raButton2.setSelected(true);
		}
		raButton1.setDisable(true);
		raButton2.setDisable(true);	
		text1.setDisable(true);
		
		button2.setOnAction(back->{
			sceondStage.close();
		});
		button1.setOnAction(change->{
			try {
			adController.changeMessage(filename,text2.getText(),text3.getText(),text4.getText(), text5.getText(),text6.getText(),select);
			PauseTransition pause=new PauseTransition(Duration.seconds(1.5));
			pause.setOnFinished(back->{
			sceondStage.close();
			});
			this.HappyEnd3("修改");
			pause.play();
			}
			catch(SomethingNullException e) {
				this.ExceptionEnd(e);
			}
			
		});
		this.setXY(la1,20,mid);this.setXY(la2,20,mid+n);this.setXY(la3, 20, mid+n*2);this.setXY(la4, 20, mid+n*3);
		this.setXY(la5, 20, mid+n*4);this.setXY(la6, 20, mid+n*5);this.setXY(la7, 20, mid+n*6);
		this.setXY(text1, i, mid);this.setXY(text2,i,mid+n);this.setXY(text3,i,mid+n*2);this.setXY(text4,i,mid+n*3);
		this.setXY(text5,i,mid+n*5);this.setXY(text6,i,mid+n*6);
		this.setXY(raButton1, i, mid+n*4);this.setXY(raButton2, i+200, mid+n*4);
		this.setXYWH(button1, 235, 650, 75,50);this.setXYWH(button2, 400, 650, 75,50);
		text1.setPrefWidth(k);text2.setPrefWidth(k);text3.setPrefWidth(k);text4.setPrefWidth(k);
		text4.setPrefWidth(k);text5.setPrefWidth(k);text6.setPrefWidth(k);
		la1.setText("登陆账号");la2.setText("登陆密码");la3.setText("真实姓名");la4.setText("联系方式");
		la5.setText("注册方式");la6.setText("工厂名称");la7.setText("工厂简介");
		la1.setFont(Font.font("Arial",20));la2.setFont(Font.font("Arial",20));la3.setFont(Font.font("Arial",20));
		la4.setFont(Font.font("Arial",20));la5.setFont(Font.font("Arial",20));la6.setFont(Font.font("Arial",20));
		la7.setFont(Font.font("Arial",20));
		root.getChildren().add(la1);root.getChildren().add(la2);root.getChildren().add(la3);root.getChildren().add(la4);
		root.getChildren().add(la5);root.getChildren().add(la6);root.getChildren().add(la7);
		root.getChildren().add(text1);root.getChildren().add(text2);root.getChildren().add(text3);root.getChildren().add(text4);
		root.getChildren().add(text5);root.getChildren().add(text6);
 	    root.getChildren().add(raButton1);root.getChildren().add(raButton2);
 	    root.getChildren().add(button1);root.getChildren().add(button2);
		Scene scene=new Scene(root,500,700);
		sceondStage.setScene(scene);
		sceondStage.show();
    }
	//第五视图的实现
	@SuppressWarnings("unchecked")
	public void displayTheFifthView() {
		int n=100;//button长度
		int i=25;//button宽度
		int k=133;//tableColumn长度
		Stage fifthStage= new Stage();
		fifthStage.setTitle("云工厂管理");
		Pane root=new Pane();
		TextField textField=new TextField();
		textField.setPromptText("在此输入名称");
		Button button1=new Button("按工厂名称查找");
		Button button2=new Button("切换");
		Button button3=new Button("返回");
		button1.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button2.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button3.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		TableView<Factory> tableView= new TableView<Factory>();
		ObservableList<Factory> data= FXCollections.observableArrayList(adController.showAllFactory(filename));
		TableColumn<Factory, Integer> IDColumn=new TableColumn<>("ID");
		TableColumn<Factory, String> nameColumn=new TableColumn<>("工厂名称");
		TableColumn<Factory, String> ownerColumn=new TableColumn<>("负责人");
		TableColumn<Factory, String> telColumn=new TableColumn<>("联系方式");
		TableColumn<Factory, String> codeColumn=new TableColumn<>("登陆账号");
		TableColumn<Factory, String> netrualColunmn=new TableColumn<>("工厂状态");
		IDColumn.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		codeColumn.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		nameColumn.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		telColumn.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		ownerColumn.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		netrualColunmn.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		Image image=new Image("file:images/11.png");
		ImageView imView=new ImageView(image);
		Image image1=new Image("file:images/22.png");
		ImageView imView1=new ImageView(image1);
		root.setStyle("-fx-background-color: white;");
		this.setXYWH(button2, 10, 125, n, i);
		this.setXYWH(button1, 185, 75, n,i);
		this.setXY(textField, 10, 75);
		this.setXY(tableView, 10, 175);
		this.setXYWH(button3, 700, 460, n, i);
		this.setXY(imView, 350,0);
		this.setXY(imView1,590, 0);
		this.setWH(imView, 250, 170);
		this.setWH(imView1, 230, 170);
		button1.setOnAction(find->{
			String name=textField.getText();
			try {
			Factory ans=adController.getFactory(filename,name);
			ObservableList<Factory> dataZ=FXCollections.observableArrayList();
			dataZ.add(ans);
			textField.clear();
			tableView.setItems(dataZ);
			}
			catch(NotFoundException e) {
				this.ExceptionEnd(e);
			}
		});
	    IDColumn.setPrefWidth(k);nameColumn.setPrefWidth(k);ownerColumn.setPrefWidth(k);
		telColumn.setPrefWidth(k);codeColumn.setPrefWidth(k);netrualColunmn.setPrefWidth(k);
		tableView.setPrefSize(800,275 );
		IDColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		ownerColumn.setCellValueFactory(new PropertyValueFactory<>("ownerName"));
		telColumn.setCellValueFactory(new PropertyValueFactory<>("ownerTel"));
		codeColumn.setCellValueFactory(new PropertyValueFactory<>("ownerZ"));
		netrualColunmn.setCellValueFactory(new PropertyValueFactory<>("netural"));
		telColumn.setCellValueFactory(new PropertyValueFactory<>("ownerTel"));
		tableView.getColumns().addAll(IDColumn,nameColumn,ownerColumn,telColumn,codeColumn,netrualColunmn);
		tableView.setItems(data);
		button3.setOnAction(event2->{
			fifthStage.close();
			this.displayTheThirdView();
		});
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		button2.setOnAction(change->{
			try {
			ObservableList<Factory> selectedRows = tableView.getSelectionModel().getSelectedItems();
			ArrayList<Factory> select=new ArrayList<Factory>(selectedRows);
            adController.changeNetural(select,filename);
			ObservableList<Factory> dataZ=FXCollections.observableArrayList(adController.showAllFactory(filename));
			tableView.setItems(dataZ);
			}
			catch(NothingContainException e) {
				this.ExceptionEnd(e);
			}
		});
		root.getChildren().add(textField);
		root.getChildren().add(button1);
		root.getChildren().add(button2);
		root.getChildren().add(tableView);
		root.getChildren().add(button3);
		root.getChildren().addAll(imView,imView1);
		
		Scene scene=new Scene(root,820,500);
		fifthStage.setScene(scene);
		fifthStage.show();
	}
	//第六视图的实现
	public void displaySixthView() {
		int n=100;//button的长度
		int i=25;//button的宽度
		Stage sixthStage=new Stage();
		sixthStage.setTitle("产品类别管理");
		Pane root =new Pane();
		TableColumn<Catagory, Integer> IDColumn=new TableColumn<>("ID");
		TableColumn<Catagory, String> catagoryColumn=new TableColumn<>("类别名称");
		IDColumn.setStyle("-fx-text-fill: RGB(0,200,127);"+"-fx-font-weight: bold;");
		catagoryColumn.setStyle("-fx-text-fill: RGB(0,200,127);"+"-fx-font-weight: bold;");
		TableView<Catagory> tableView=new TableView<Catagory>();
		ObservableList<Catagory> data= FXCollections.observableArrayList(adController.showAllProductCatagory(filename));
		Button button1=new Button("按类别名称查询");
		Button button2=new Button("重置");
		Button button3=new Button("新建");
		Button button4=new Button("删除");
		Button button5=new Button("返回");
		button1.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button2.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button3.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button4.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button5.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		TextField textField=new TextField();
		Image image=new Image("file:images/11.png");
		ImageView imView=new ImageView(image);
		Image image1=new Image("file:images/22.png");
		ImageView imView1=new ImageView(image1);
		root.setStyle("-fx-background-color: white;");
		textField.setPromptText("在此输入名字");
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		button1.setOnAction(research->{
			try {
			String name=textField.getText();
			Catagory ans=adController.getACatagory(filename, name);
			ObservableList<Catagory> DataZ=FXCollections.observableArrayList();
			DataZ.add(ans);
			textField.clear();
			tableView.setItems(DataZ);
			}
			catch(NotFoundException e) {
				this.ExceptionEnd(e);
			}
		});
		button2.setOnAction(reset->{
			ObservableList<Catagory>dataZ=FXCollections.observableArrayList(adController.showAllProductCatagory(filename));
			tableView.setItems(dataZ);
		});
		button4.setOnAction(remove->{
			ObservableList<Catagory> select=tableView.getSelectionModel().getSelectedItems();
			ArrayList<Catagory> removions=new ArrayList<Catagory>(select);
			try {
				adController.checkNullc(removions);
				this.checkView1(removions);
			}
			catch(NothingContainException e) {
				this.ExceptionEnd(e);
			}
		});
		button3.setOnAction(add->{
			this.addView();
		});
		button5.setOnAction(back->{
			sixthStage.close();
			this.displayTheThirdView();
		});
		this.setXY(textField,10, 50);
		this.setXYWH(button1, 195, 50, n, i);
		this.setXYWH(button2, 310, 50, n, i);
		this.setXY(imView1,500, 0);
		this.setWH(imView1, 150, 150);
		this.setXYWH(button3, 10, 125, n, i);
		this.setXYWH(button4, 140, 125, n, i);
		this.setXYWH(button5, 575, 570, n, i);
		this.setXY(tableView, 40, 155);
		tableView.setPrefSize(600, 400);
		IDColumn.setPrefWidth(300);
		catagoryColumn.setPrefWidth(300);
		IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		catagoryColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		////////////////////////////////////////////////////////////////
		tableView.setItems(data);
		root.getChildren().add(textField);
		root.getChildren().add(button1);
		root.getChildren().add(button2);
		root.getChildren().add(button3);
		root.getChildren().add(button4);
		root.getChildren().add(imView1);
		tableView.getColumns().add(IDColumn);
		tableView.getColumns().add(catagoryColumn);
		root.getChildren().add(tableView);
		root.getChildren().add(button5);
		Scene scene=new Scene(root,700,600);
		sixthStage.setScene(scene);
		sixthStage.show();
	}
	//与第六视图配套的新建视图
	public void addView() {
		int n=100;//button长度
		int k=25;//button宽度
		Stage  stage=new Stage();
		Pane root=new Pane();
		stage.setTitle("新建产品类");
		Label label=new Label();
		TextField textField=new TextField();
		Button button1=new Button("返回");
		Button button2=new Button("确定");
		textField.setPromptText("在此输入名称:");
		textField.setPrefSize(200, 40);
		textField.setStyle("-fx-font-size: 16px;");
		button1.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button2.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		label.setText("请在下方输入新产品类的名称");
		label.setStyle("-fx-text-fill: RGB(255,160,0);"+"-fx-font-weight: bold;");
		label.setFont(new Font(20));
		this.setXY(label, 20, 20);
		this.setXY(textField, 50,65 );
		this.setXYWH(button1, 30,120, n, k);
		this.setXYWH(button2, 170,120, n, k);
		button1.setOnAction(back->{
			stage.close();
		});
		button2.setOnAction(add->{
			try {
			adController.addCatagory(filename,textField.getText());
			stage.close();
			this.HappyEnd3("新建");
			}
			catch(SameCodeException e) {
				this.ExceptionEnd(e);
			}
			catch(SomethingNullException e) {
				this.ExceptionEnd(e);
			}
		});
		root.getChildren().add(label);
		root.getChildren().add(textField);
		root.getChildren().add(button1);
		root.getChildren().add(button2);
		Scene scene=new Scene(root,300,160);
		stage.setScene(scene);
		stage.show();
	}
	//配套的确认删除视图
	public void checkView1(ArrayList<Catagory> removions) {
		Stage stage =new Stage();
		Pane root=new Pane();
		Button button1=new Button("确认");
		Button button2=new Button("再想想");
		button1.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button2.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		Label label=new Label();
		label.setText("请再次确认是否要删除哦>.<");
		label.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		label.setFont(new Font(14));
		this.setXY(label, 10, 25);
		this.setXYWH(button2, 20, 55, 65, 25);
		this.setXYWH(button1, 105, 55, 65,25);
		button1.setOnAction(remove->{
			try {
			adController.removeProductCatagory(removions,filename);
			stage.close();
			this.HappyEnd3("删除");
			}
			catch(WasQuotedException e) {
				this.ExceptionEnd(e);
			}
		});
		button2.setOnAction(back->{
			stage.close();
		});
		root.getChildren().add(button1);
		root.getChildren().add(button2);
		root.getChildren().add(label);
		Scene scene=new Scene(root,200,100);
		stage.setScene(scene);
		stage.show();
	}
	//第七视图的实现
	public void displayTheSeventhView() {
		int n=100;//button长度
		int i=25;//button宽度
		double k=130;
		Stage seventhStage =new Stage();
		seventhStage.setTitle("产品信息管理");
		TextField textField=new TextField();
		Button button2=new Button("重置");
		Button button1=new Button("查询");
		Button button3=new Button("新建");
		Button button4=new Button("删除");
		Button button5=new Button("修改");
		Button button6=new Button("返回");
		button1.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button2.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button3.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button4.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button5.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button6.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		Image image=new Image("file:images/11.png");
		ImageView imView=new ImageView(image);
		Image image1=new Image("file:images/22.png");
		ImageView imView1=new ImageView(image1);
		textField.setPromptText("在此输入类别:");
		textField.setFocusTraversable(false);
		button2.requestFocus();
		TableView <Product>tableView=new TableView<Product>();
		tableView.setFocusTraversable(false);
		ObservableList<Product> data=FXCollections.observableArrayList(adController.showAllProduct(filename));
		TableColumn<Product, Integer> IdColumn=new TableColumn<>("ID");
		TableColumn<Product, String> codeColumn=new TableColumn<>("产品编号");
		TableColumn<Product, String> nameColumn=new TableColumn<>("产品名称");
		TableColumn<Product, String> catagoryColumn=new TableColumn<>("产品类别");
		TableColumn<Product, String> sizeColumn=new TableColumn<>("产品规格");
 		TableColumn<Product, String> desColumn=new TableColumn<>("产品描述");
		IdColumn.setStyle("-fx-text-fill: RGB(3,168,158);"+"-fx-font-weight: bold;");
		codeColumn.setStyle("-fx-text-fill: RGB(3,168,158);"+"-fx-font-weight: bold;");
		nameColumn.setStyle("-fx-text-fill: RGB(3,168,158);"+"-fx-font-weight: bold;");
		catagoryColumn.setStyle("-fx-text-fill: RGB(3,168,158);"+"-fx-font-weight: bold;");
		sizeColumn.setStyle("-fx-text-fill: RGB(3,168,158);"+"-fx-font-weight: bold;");
		desColumn.setStyle("-fx-text-fill: RGB(3,168,158);"+"-fx-font-weight: bold;");
		IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		catagoryColumn.setCellValueFactory(new PropertyValueFactory<>("catagory"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
		desColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		Pane root=new Pane();	
		root.setStyle("-fx-background-color: white;");
		/////////////////////////////////////////////
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		button1.setOnAction(research->{
			try {
			String catagoryName=textField.getText();
			ArrayList<Product> ans=adController.getProduct(filename, catagoryName);
			ObservableList<Product> DataZ=FXCollections.observableArrayList(ans);
			tableView.setItems(DataZ);
			}
			catch(NotFoundException e) {
				this.ExceptionEnd(e);
			}
		});
		button2.setOnAction(reset->{
			ObservableList<Product> DataZ=FXCollections.observableArrayList(adController.showAllProduct(filename));
			tableView.setItems(DataZ);
		});
		button3.setOnAction(add->{
			this.addView7();
		});
		button4.setOnAction(remove->{
			ObservableList<Product> select=tableView.getSelectionModel().getSelectedItems();
			ArrayList<Product> removtions=new ArrayList<>(select);
			try {
				adController.checkNullp(removtions);
				this.checkView2(removtions);
			}
			catch(NothingContainException e) {
				this.ExceptionEnd(e);
			}
		});
		button5.setOnAction(change->{
			ObservableList<Product> select=tableView.getSelectionModel().getSelectedItems();
			ArrayList<Product> removions=new ArrayList<>(select);
	    	try {
	    		adController.checkNullp(removions);
	    		adController.checkMultiplep(removions);
	    		this.changeView7(removions);
	    	}
	    	catch(NothingContainException e) {
	    		this.ExceptionEnd(e);
	    	}
	    	catch(MultipleSelectException e) {
	    		this.ExceptionEnd(e);
	    	}
			
		});
		button6.setOnAction(back->{
			seventhStage.close();
			this.displayTheThirdView();
		});
		tableView.setItems(data);
		this.setXY(tableView, 10, 150);
		tableView.setPrefSize(773, 400);
		IdColumn.setPrefWidth(k);codeColumn.setPrefWidth(k);nameColumn.setPrefWidth(k);
		catagoryColumn.setPrefWidth(k);sizeColumn.setPrefWidth(k);desColumn.setPrefWidth(k);
		this.setXY(textField, 10, 50);
		this.setXYWH(button1, 175, 50, n, i);
		this.setXYWH(button2, 290, 50, n, i);
		this.setXY(imView, 450, 0);
		this.setWH(imView, 200, 140);
		this.setXYWH(button3, 10, 115, n, i);
		this.setXYWH(button4, 125, 115, n, i);
		this.setXYWH(button5, 675, 115, n, i);
		this.setXYWH(button6, 690, 565, n, i);
		tableView.getColumns().add(IdColumn);
		tableView.getColumns().add(codeColumn);
		tableView.getColumns().add(nameColumn);
		tableView.getColumns().add(catagoryColumn);
		tableView.getColumns().add(sizeColumn);
		tableView.getColumns().add(desColumn);
		root.getChildren().add(textField);
		root.getChildren().add(tableView);
		root.getChildren().add(button1);
		root.getChildren().add(button2);
		root.getChildren().add(button3);
		root.getChildren().add(button4);
		root.getChildren().add(button5);
		root.getChildren().add(button6);
		root.getChildren().add(imView);
		Scene scene=new Scene(root,800,600);
		seventhStage.setScene(scene);
		seventhStage.show();
	}
	//第七视图配套的新建视图7 
	public void addView7(){
		int n=100;//
    	int k=25;
    	int w=250;//文本框长度
    	int g=25;//文本框宽度
    	Stage stage=new Stage();
    	stage.setTitle("产品新建");
    	Pane root=new Pane();
    	Label label=new Label();
    	ArrayList<Catagory> catagorys=adController.showAllProductCatagory(filename);
    	Iterator<Catagory> iterator=catagorys.iterator();
    	label.setText("产品注册");
    	Button button1=new Button("确定");  
    	Button button2=new Button("返回");
    	button1.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
    	button2.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
    	root.requestFocus();
    	TextField text1=new TextField();    	
    	text1.setFocusTraversable(false);
    	TextField text2=new TextField();
    	text2.setFocusTraversable(false);
    	TextField text3=new TextField();
    	text3.setFocusTraversable(false);
    	TextField text4=new TextField(); 
    	text4.setFocusTraversable(false);
        Image backgroundImage = new Image("file:image/77.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setOpacity(0.6);
        this.setWH(backgroundImageView, 500,400);
        root.getChildren().add(backgroundImageView);
    	ComboBox<String> combox=new ComboBox<String>();
    	while(iterator.hasNext()) {
    		Catagory catagory=iterator.next();
    		combox.getItems().add(catagory.getName());
    	}
    	combox.setPromptText("产品类别");
    	combox.setPrefSize(110, 25);
        button1.setOnAction(add->{
        	try {
        	String catagory= combox.getValue();
    		String code=text1.getText();
    		String name=text2.getText();
    		String size=text3.getText();
    		String des=text4.getText();
    		adController.addNewProduct(filename, name, catagory, code, size, des);
    		PauseTransition pause=new PauseTransition(Duration.seconds(1.5));
    		pause.setOnFinished(good->{
    			stage.close();
    		});
    		this.HappyEnd3("新建");
    		pause.play();
        	}
        	catch(SomethingNullException e) {
        		this.ExceptionEnd(e);
        	}
        	catch(SameCodeException e) {
        		this.ExceptionEnd(e);
        	}
        });
    	button2.setOnAction(back->{
    		stage.close();
    	});
    	text1.setPromptText("新产品的编号：");
    	text2.setPromptText("新产品的名称:");
    	text3.setPromptText("新产品的规格:");
    	text4.setPromptText("新产品的描述:");
    	text1.setPrefSize(w, g);
    	text2.setPrefSize(w, g);
    	text3.setPrefSize(w, g);
    	text4.setPrefSize(w, g);
    	this.setXY(combox, 370, 40);
    	this.setXY(text1,20, 40);
    	this.setXY(text2, 20, 120);
    	this.setXY(text3, 20, 200);
    	this.setXY(text4, 20, 280);
    	this.setXYWH(button2, 175,350, n, k);
    	this.setXYWH(button1, 285, 350, n, k);
    	root.getChildren().addAll(text1,text2,text3,text4,button1,button2);
    	root.getChildren().add(combox);
    	Scene scene=new Scene(root,500,400);
    	stage.setScene(scene);
    	stage.show();
	}
	//配套的删除视图7
	public void checkView2(ArrayList<Product> removions) {
		Stage stage =new Stage();
		Pane root=new Pane();
		Button button1=new Button("确认");
		Button button2=new Button("再想想");
		button1.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button2.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		Label label=new Label();
		label.setText("请再次确认是否要删除哦>.<");
		label.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		label.setFont(new Font(14));
		this.setXY(label, 10, 25);
		this.setXYWH(button2, 20, 55, 65, 25);
		this.setXYWH(button1, 105, 55, 65,25);
		button1.setOnAction(remove->{
			adController.removeProduct(filename, removions);
			stage.close();
			this.HappyEnd3("删除");
		});
		button2.setOnAction(back->{
			stage.close();
		});
		root.getChildren().add(button1);
		root.getChildren().add(button2);
		root.getChildren().add(label);
		Scene scene=new Scene(root,200,100);
		stage.setScene(scene);
		stage.show();
	}
	//配套的修改视图7
	public void changeView7(ArrayList<Product>select) {
		int n=100;//
    	int k=25;
    	int w=250;//文本框长度
    	int g=25;//文本框宽度
    	Product product=select.get(0);
    	Stage stage=new Stage();
    	stage.setTitle("产品新建");
    	Pane root=new Pane();
    	Label label1=new Label();
    	label1.setText("产品编号");
    	Label label2=new Label();
    	label2.setText("产品名称");
    	Label label3=new Label();
    	label3.setText("产品尺寸");
    	Label label4=new Label();
    	label4.setText("产品描述");
    	Label label5=new Label();
    	label5.setText("产品类别");
		label1.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		label2.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		label3.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		label4.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		label5.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
    	ArrayList<Catagory> catagorys=adController.showAllProductCatagory(filename);
    	Iterator<Catagory> iterator=catagorys.iterator();
    	Button button1=new Button("确定"); 
    	button1.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
    	Button button2=new Button("返回");
    	button2.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");

        Image backgroundImage = new Image("file:images/77.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setOpacity(0.6);
        this.setWH(backgroundImageView, 500,400);
        root.getChildren().add(backgroundImageView);
    	root.requestFocus();
    	TextField text1=new TextField(product.getCode());    	
    	text1.setFocusTraversable(false);
    	text1.setDisable(true);
    	TextField text2=new TextField(product.getName());
    	text2.setFocusTraversable(false);
    	TextField text3=new TextField(product.getSize());
    	text3.setFocusTraversable(false);
    	TextField text4=new TextField(product.getDescription()); 
    	text4.setFocusTraversable(false);
    	ComboBox<String> combox=new ComboBox<String>();
    	while(iterator.hasNext()) {
    		Catagory catagory=iterator.next();
    		combox.getItems().add(catagory.getName());
    	}
    	
    	combox.setPromptText(product.getCatagory());
    	combox.setValue(product.getCatagory());
    	combox.setPrefSize(110, 25);
        button1.setOnAction(add->{try {
        	String name=text2.getText();
        	String size=text3.getText();
        	String des=text4.getText();
        	String Catagory=combox.getValue();
        	adController.changeProduct(select,filename, name, Catagory, size, des);
    		PauseTransition pause=new PauseTransition(Duration.seconds(1.5));
    		pause.setOnFinished(good->{
    			stage.close();
    		});
    		this.HappyEnd3("修改");
    		pause.play();
        }
        catch(SomethingNullException e) {
        	this.ExceptionEnd(e);
        }
        });
    	button2.setOnAction(back->{
    		stage.close();
    	});
    	text1.setPromptText("产品的编号：");
    	text2.setPromptText("产品的名称:");
    	text3.setPromptText("产品的规格:");
    	text4.setPromptText("产品的描述:");
    	text1.setPrefSize(w, g);
    	text2.setPrefSize(w, g);
    	text3.setPrefSize(w, g);
    	text4.setPrefSize(w, g);
    	this.setXY(combox, 370, 40);
    	this.setXY(label5, 370, 25);
    	this.setXY(text1,20, 40);
    	this.setXY(label1, 20, 25);
    	this.setXY(text2, 20, 120);
    	this.setXY(label2, 20, 105);
    	this.setXY(text3, 20, 200);
    	this.setXY(label3, 20, 185);
    	this.setXY(text4, 20, 280);
    	this.setXY(label4, 20, 265);
    	this.setXYWH(button2, 175,350, n, k);
    	this.setXYWH(button1, 285, 350, n, k);
    	root.getChildren().addAll(text1,text2,text3,text4,button1,button2);
    	root.getChildren().add(combox);
    	root.getChildren().addAll(label1,label2,label3,label4,label5);
    	Scene scene=new Scene(root,500,400);
    	stage.setScene(scene);
    	stage.show();
    }
    //第八视图的配套设备类视图
    public void ECatagoryView() {
		int n=100;//button的长度
		int i=25;//button的宽度
		Stage sixthStage=new Stage();
		sixthStage.setTitle("设备类别管理");
		Pane root =new Pane();
		TableColumn<Catagory, Integer> IDColumn=new TableColumn<>("ID");
		TableColumn<Catagory, String> catagoryColumn=new TableColumn<>("类别名称");
		IDColumn.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		catagoryColumn.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		TableView<Catagory> tableView=new TableView<Catagory>();
		ObservableList<Catagory> data= FXCollections.observableArrayList(adController.ShowAllEquipmentCatagory(filename));
		Button button1=new Button("按类别名称查询");
		Button button2=new Button("重置");
		Button button3=new Button("新建");
		Button button4=new Button("删除");
		Button button5=new Button("返回");
		button1.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button2.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button3.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button4.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button5.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");

		TextField textField=new TextField();
		Image image=new Image("file:images/11.png");
		ImageView imView=new ImageView(image);
		Image image1=new Image("file:images/22.png");
		ImageView imView1=new ImageView(image1);
		root.setStyle("-fx-background-color: white;");
		textField.setPromptText("在此输入名字");
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		button1.setOnAction(research->{
			try {
			String name=textField.getText();
			Catagory ans=adController.getAEquipmentCatagory(filename,name);
			ObservableList<Catagory> DataZ=FXCollections.observableArrayList();
			DataZ.add(ans);
			textField.clear();
			tableView.setItems(DataZ);
			}
			catch(NotFoundException e) {
				this.ExceptionEnd(e);
			}
		});
		button2.setOnAction(reset->{
			ObservableList<Catagory>dataZ=FXCollections.observableArrayList(adController.ShowAllEquipmentCatagory(filename));
			tableView.setItems(dataZ);
		});
		button4.setOnAction(remove->{
			ObservableList<Catagory> select=tableView.getSelectionModel().getSelectedItems();
			ArrayList<Catagory> removions=new ArrayList<Catagory>(select);
			try {
				adController.checkNullc(removions);
				this.checkView8(removions);
			}
			catch(NothingContainException e) {
				this.ExceptionEnd(e);
			}
			
		});
		button3.setOnAction(add->{
			this.addView8();
		});
		button5.setOnAction(back->{
			sixthStage.close();
			this.displayTheThirdView();
		});
		this.setXY(textField,10, 50);
		this.setXYWH(button1, 195, 50, n, i);
		this.setXYWH(button2, 310, 50, n, i);
		this.setXYWH(button3, 10, 125, n, i);
		this.setXYWH(button4, 140, 125, n, i);
		this.setXYWH(button5, 575, 570, n, i);
		this.setXY(tableView, 40, 155);
		this.setXY(imView, 450, 0);
		this.setWH(imView, 200, 140);
		tableView.setPrefSize(600, 400);
		IDColumn.setPrefWidth(300);
		catagoryColumn.setPrefWidth(300);
		IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		catagoryColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		////////////////////////////////////////////////////////////////
		tableView.setItems(data);
		root.getChildren().add(textField);
		root.getChildren().add(button1);
		root.getChildren().add(button2);
		root.getChildren().add(button3);
		root.getChildren().add(button4);
		tableView.getColumns().add(IDColumn);
		tableView.getColumns().add(catagoryColumn);
		root.getChildren().add(tableView);
		root.getChildren().add(button5);
		root.getChildren().add(imView);
		Scene scene=new Scene(root,700,600);
		sixthStage.setScene(scene);
		sixthStage.show();
    }
    //配套的新建设备类视图
    public void addView8() {
    	int n=100;//button长度
		int k=25;//button宽度
		Stage  stage=new Stage();
		Pane root=new Pane();
		stage.setTitle("新建设备类");
		Label label=new Label();
		TextField textField=new TextField();
		Button button1=new Button("返回");
		Button button2=new Button("确定");
		button1.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button2.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		label.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		textField.setPromptText("在此输入名称:");
		textField.setPrefSize(200, 40);
		textField.setStyle("-fx-font-size: 16px;");
		label.setText("请在下方输入新设备类的名称");
		label.setFont(new Font(20));
		this.setXY(label, 20, 20);
		this.setXY(textField, 50,65 );
		this.setXYWH(button1, 30,120, n, k);
		this.setXYWH(button2, 170,120, n, k);
		button1.setOnAction(back->{
			stage.close();
		});
		button2.setOnAction(add->{
			try {
			adController.addEquipmentCatagory(filename,textField.getText());
			stage.close();
			this.HappyEnd3("新建");
			}
			catch(SomethingNullException e) {
				this.ExceptionEnd(e);
			}
			catch(SameCodeException e) {
				this.ExceptionEnd("名称重复了喵~,换个名称吧");
			}
		});
		root.getChildren().add(label);
		root.getChildren().add(textField);
		root.getChildren().add(button1);
		root.getChildren().add(button2);
		Scene scene=new Scene(root,300,160);
		stage.setScene(scene);
		stage.show();
    }
    //配套的确认删除设备类视图
    public void checkView8(ArrayList<Catagory>removions) {
		Stage stage =new Stage();
		Pane root=new Pane();
		Button button1=new Button("确认");
		Button button2=new Button("再想想");
		button1.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button2.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		Label label=new Label();
		label.setText("请再次确认是否要删除哦>.<");
		label.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		label.setFont(new Font(14));
		this.setXY(label, 10, 25);
		this.setXYWH(button2, 20, 55, 65, 25);
		this.setXYWH(button1, 105, 55, 65,25);
		button1.setOnAction(remove->{
			try {
			adController.removeEquipmentCatagory(removions,filename);
			this.HappyEnd3("删除");
			}
			catch(WasQuotedException e) {
				this.ExceptionEnd(e);
			}
			finally {
				stage.close();
			}
		});
		button2.setOnAction(back->{
			stage.close();
		});
		root.getChildren().add(button1);
		root.getChildren().add(button2);
		root.getChildren().add(label);
		Scene scene=new Scene(root,200,100);
		stage.setScene(scene);
		stage.show();
    }
	//第八视图的实现
	@SuppressWarnings("unchecked")
	public void displayTheEighthView() {
		int n=100;//button长度
		int i=25;//button宽度
		int k=100;
		Stage eightthStage=new Stage();
		eightthStage.setTitle("设备管理界面");
		Pane root=new Pane();
		TextField textField=new TextField();
		textField.setPromptText("在此输入类别名称");
		Button button1=new Button("按类别检索");
		Button button2=new Button("重置");
		Button button3=new Button("新增");
		Button button4=new Button("删除");
		Button button5=new Button("返回");
		Button button6=new Button("修改");
		Button button7=new Button("设备状态");
		Button button8=new Button("强制归还");
		button1.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button2.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button3.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button4.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button5.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button6.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button7.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button8.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		ObservableList<Equipment> data=FXCollections.observableArrayList(adController.showAllEquipments(filename));
		TableView<Equipment> tableView=new TableView<Equipment>(); 
		textField.setFocusTraversable(false);
		tableView.setFocusTraversable(false);
		TableColumn<Equipment, Integer> IdColumn=new TableColumn<>("ID");
		TableColumn<Equipment, String> nameColumn=new TableColumn<>("设备名称");
		TableColumn<Equipment, String> codeColumn=new TableColumn<>("设备编号");
		TableColumn<Equipment, String> catagoryColumn=new TableColumn<>("设备类别");
		TableColumn<Equipment, String> sizeColumn=new TableColumn<>("设备规格");
		TableColumn<Equipment, String> netural1Column=new TableColumn<>("设备状态");
		TableColumn<Equipment, String> desColumn= new TableColumn<>("设备描述");
		TableColumn<Equipment,String> netural2Column=new TableColumn<>("租用状态");
		TableColumn<Equipment,String> belongColumn=new TableColumn<>("所属工厂");
		IdColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		codeColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		nameColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		catagoryColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		sizeColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		desColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		netural1Column.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		netural2Column.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		belongColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		Image image=new Image("file:images/00.jpg");
		ImageView imView=new ImageView(image);
		Image image1=new Image("file:images/22.png");
		ImageView imView1=new ImageView(image1);
		root.setStyle("-fx-background-color: white;");
		/////////////////////////////////////
		IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		catagoryColumn.setCellValueFactory(new PropertyValueFactory<>("catagory"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
		netural1Column.setCellValueFactory(new PropertyValueFactory<>("netural1"));
		desColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		netural2Column.setCellValueFactory(new PropertyValueFactory<>("netural2"));
		belongColumn.setCellValueFactory(new PropertyValueFactory<>("belong"));
		IdColumn.setPrefWidth(k);codeColumn.setPrefWidth(k);nameColumn.setPrefWidth(k);
		catagoryColumn.setPrefWidth(k);sizeColumn.setPrefWidth(k);netural1Column.setPrefWidth(k);
		desColumn.setPrefWidth(k);netural2Column.setPrefWidth(k);belongColumn.setPrefWidth(k);
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		button1.setOnAction(research->{
			try {
			String catagoryName=textField.getText();
			textField.clear();
			ObservableList<Equipment> dataZ=FXCollections.observableArrayList(adController.getEquipment(filename,catagoryName));
			tableView.setItems(dataZ);
			}
			catch(NotFoundException e) {
				this.ExceptionEnd(e);
			}
		});
		button2.setOnAction(reset->{
			ObservableList<Equipment> dataZ=FXCollections.observableArrayList(adController.showAllEquipments(filename));
			tableView.setItems(dataZ);
		});
		button3.setOnAction(add->{
			this.addView8A();
		});
		button4.setOnAction(remove->{
			ObservableList<Equipment> select=tableView.getSelectionModel().getSelectedItems();
			ArrayList<Equipment> removions=new ArrayList<>(select);
			try {
				adController.checkNull(removions);
				adController.checkAgain(removions);
				this.checkView8B(removions);
			}
			catch(NomalException e) {
				this.ExceptionEnd("这是别人的设备，不能乱删除喵~");
			}
			catch(NothingContainException e) {
			this.ExceptionEnd(e);
		}
		});
		button5.setOnAction(back->{
			eightthStage.close();
			this.displayTheThirdView();
		});
		button6.setOnAction(change->{
			ArrayList<Equipment> select=new ArrayList<Equipment>(tableView.getSelectionModel().getSelectedItems());
	    	try {
	    		adController.checkNull(select);
	    		adController.checkMultiple(select);
	    		this.changView8C(select);
	    	}
	    	catch(NothingContainException e){
	    		this.ExceptionEnd(e);
	    	}
	    	catch(MultipleSelectException e) {
	    		this.ExceptionEnd(e);
	    	}
		});
		button7.setOnAction(netural->{
			try {
			ObservableList<Equipment>select=tableView.getSelectionModel().getSelectedItems();
			ArrayList<Equipment> selections=new ArrayList<Equipment>(select);
			adController.turnOn_Off(filename, selections);
			this.HappyEnd3("开/关机");
			}
			catch(NothingContainException e) {
				this.ExceptionEnd(e);
			}
		});
		button8.setOnAction(returnForced->{
			try {
			ObservableList<Equipment>select=tableView.getSelectionModel().getSelectedItems();
			ArrayList<Equipment> returnions=new ArrayList<Equipment>(select);
			adController.checkNull(returnions);
			adController.returnEquipment(filename, returnions);
			this.HappyEnd3("强制归还");
			}
			catch(SomethingNullException e) {
				this.ExceptionEnd("里面没有已租借的设备喵~");
			}
			catch(NomalException e) {
				this.ExceptionEnd("别人自己的设备不能强制归还喵~");
			}
			catch(NothingContainException e) {
				this.ExceptionEnd(e);
			}
		});
		this.setXY(textField, 10, 25);
		this.setXYWH(button3, 10, 150, n, i);
		this.setXYWH(button4, 130, 150, n, i);
		this.setXYWH(button1, 175, 25, n, i);
		this.setXYWH(button2, 290, 25, n, i);
		this.setXYWH(button5, 810, 570, n, i);
		this.setXYWH(button6, 810, 150, n, i);
		this.setXYWH(button7, 700, 150, n, i);
		this.setXYWH(button8, 250, 150, n, i);
		this.setXY(tableView, 10, 180);
		this.setXY(imView1, 450, 0);
		this.setWH(imView1, 200, 180);
		this.setWH1(imView, 713, 0, 190,150);
		tableView.setPrefSize(899, 375);
		tableView.setItems(data);
		tableView.getColumns().addAll(IdColumn,codeColumn,nameColumn,catagoryColumn,sizeColumn,netural1Column);
		tableView.getColumns().addAll(desColumn,netural2Column,belongColumn);
		root.getChildren().add(textField);
		root.getChildren().add(button1);
		root.getChildren().add(button2);
		root.getChildren().add(button3);
		root.getChildren().add(button4);
		root.getChildren().add(button5);
		root.getChildren().add(button6);
		root.getChildren().add(button7);
		root.getChildren().add(tableView);
		root.getChildren().add(imView1);
		root.getChildren().add(imView);
		root.getChildren().add(button8);
		Scene scene=new Scene(root,950,600);
		eightthStage.setScene(scene);
		eightthStage.show();
	}
	//与第八视图配套的新建视图
	public void addView8A() {
		int n=100;//
    	int k=25;
    	int w=250;//文本框长度
    	int g=25;//文本框宽度
    	Stage stage=new Stage();
    	stage.setTitle("设备新建");
    	Pane root=new Pane();
    	Label label=new Label();
    	ArrayList<Catagory> catagorys=adController.ShowAllEquipmentCatagory(filename);
    	Iterator<Catagory> iterator=catagorys.iterator();
    	label.setText("设备注册");
    	Button button1=new Button("确定");  
    	Button button2=new Button("返回");
    	button1.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
    	button2.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
    	root.requestFocus();
    	TextField text1=new TextField();    	
    	text1.setFocusTraversable(false);
    	TextField text2=new TextField();
    	text2.setFocusTraversable(false);
    	TextField text3=new TextField();
    	text3.setFocusTraversable(false);
    	TextField text4=new TextField(); 
    	text4.setFocusTraversable(false);
    	ComboBox<String> combox=new ComboBox<String>();
        Image backgroundImage = new Image("file:images/77.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setOpacity(0.6);
        this.setWH(backgroundImageView, 500,400);
        root.getChildren().add(backgroundImageView);
    	while(iterator.hasNext()) {
    		Catagory catagory=iterator.next();
    		combox.getItems().add(catagory.getName());
    	}
    	combox.setPromptText("设备类别");
    	combox.setPrefSize(110, 25);
        button1.setOnAction(add->{
        	
        	try {
        	String catagory= combox.getValue();
    		String code=text1.getText();
    		String name=text2.getText();
    		String size=text3.getText();
    		String des=text4.getText();
    		adController.addNewEquipment(filename, name,code,catagory,size,des);
    		PauseTransition pause=new PauseTransition(Duration.seconds(1.5));
    		pause.setOnFinished(good->{
    			stage.close();
    		});
    		this.HappyEnd3("新建");
    		pause.play();
        	}
        	catch(SomethingNullException e) {
        		this.ExceptionEnd(e);
        	}
        	catch(SameCodeException e) {
        		this.ExceptionEnd(e);
        	}
        });
    	button2.setOnAction(back->{
    		stage.close();
    	});
    	text1.setPromptText("新设备的编号：");
    	text2.setPromptText("新设备的名称:");
    	text3.setPromptText("新设备的规格:");
    	text4.setPromptText("新设备的描述:");
    	text1.setPrefSize(w, g);
    	text2.setPrefSize(w, g);
    	text3.setPrefSize(w, g);
    	text4.setPrefSize(w, g);
    	this.setXY(combox, 370, 20);
    	this.setXY(text1,20, 20);
    	this.setXY(text2, 20, 90);
    	this.setXY(text3, 20, 160);
    	this.setXY(text4, 20, 230);
    	this.setXYWH(button2, 175,350, n, k);
    	this.setXYWH(button1, 285, 350, n, k);
    	root.getChildren().addAll(text1,text2,text3,text4,button1,button2);
    	root.getChildren().add(combox);
    	Scene scene=new Scene(root,500,400);
    	stage.setScene(scene);
    	stage.show();
	}
	//与第八视图配套的删除视图
	public void checkView8B(ArrayList<Equipment>removions) {
		Stage stage =new Stage();
		Pane root=new Pane();
		Button button1=new Button("确认");
		Button button2=new Button("再想想");
		button1.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		button2.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		Label label=new Label();
		label.setText("请再次确认是否要删除哦>.<");
		label.setFont(new Font(14));
		label.setStyle("-fx-text-fill: RGB(51,161,201);"+"-fx-font-weight: bold;");
		this.setXY(label, 10, 25);
		this.setXYWH(button2, 20, 55, 65, 25);
		this.setXYWH(button1, 105, 55, 65,25);
		button1.setOnAction(remove->{
			adController.removeEquipment(removions,filename);
			PauseTransition pause=new PauseTransition(Duration.seconds(1.5));
			pause.setOnFinished(back->{
				stage.close();
			});
			this.HappyEnd3("删除");
			pause.play();
		});
		button2.setOnAction(back->{
			stage.close();
		});
		root.getChildren().add(button1);
		root.getChildren().add(button2);
		root.getChildren().add(label);
		Scene scene=new Scene(root,200,100);
		stage.setScene(scene);
		stage.show();
	}
	//修改视图
	public void changView8C(ArrayList<Equipment>select) {
		int n=100;//
    	int k=25;
    	int w=250;//文本框长度
    	int g=25;//文本框宽度
    	Stage stage=new Stage();
    	stage.setTitle("设备新建");
    	Pane root=new Pane();
    	Label label1=new Label("编号");
    	Label label2=new Label("名称");
    	Label label3=new Label("规格");
    	Label label4=new Label("描述");
    	Label label5=new Label("所属工厂");
    	Label label6=new Label("设备类别");
		label1.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		label2.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		label3.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		label4.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		label5.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		label6.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
    	Equipment equipment=select.get(0);
    	ArrayList<Catagory> catagorys=adController.ShowAllEquipmentCatagory(filename);
    	Iterator<Catagory> iterator=catagorys.iterator();
    	Button button1=new Button("确定");  
    	Button button2=new Button("返回");
    	button1.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
    	button2.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
        Image backgroundImage = new Image("file:images/77.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setOpacity(0.6);
        this.setWH(backgroundImageView, 500,400);
        root.getChildren().add(backgroundImageView);
    	root.requestFocus();
    	TextField text1=new TextField(equipment.getCode());    	
    	text1.setFocusTraversable(false);
    	text1.setDisable(true);
    	TextField text2=new TextField(equipment.getName());
    	text2.setFocusTraversable(false);
    	TextField text3=new TextField(equipment.getSize());
    	text3.setFocusTraversable(false);
    	TextField text4=new TextField(equipment.getDescription()); 
    	text4.setFocusTraversable(false);
    	TextField text5=new TextField(equipment.getBelong()); 
    	text5.setFocusTraversable(false);
    	text5.setDisable(true);
    	ComboBox<String> combox=new ComboBox<String>();
    	while(iterator.hasNext()) {
    		Catagory catagory=iterator.next();
    		combox.getItems().add(catagory.getName());
    	}
    	combox.setPromptText(equipment.getCatagory());
    	combox.setValue(equipment.getCatagory());
    	combox.setPrefSize(110, 25);
        button1.setOnAction(change->{
        	try {
        	String code=text1.getText();
        	String name=text2.getText();
        	String catagory=combox.getValue();
        	String size=text3.getText();
        	String des=text4.getText();
        	String belong=text5.getText();
        	adController.changeEquipment(filename, code, name, catagory, size, des, belong);
        	PauseTransition pause=new PauseTransition(Duration.seconds(1.5));
        	pause.setOnFinished(back->{
        		stage.close();
        	});
        	this.HappyEnd3("修改");
        	pause.play();
        	}
        	catch(SomethingNullException e) {
        		this.ExceptionEnd(e);
        	}
        });
    	button2.setOnAction(back->{
    		stage.close();
    	});
    	text1.setPromptText("设备的编号：");
    	text2.setPromptText("设备的名称:");
    	text3.setPromptText("设备的规格:");
    	text4.setPromptText("设备的描述:");
    	text5.setPromptText("设备的所属工厂");
    	text1.setPrefSize(w, g);
    	text2.setPrefSize(w, g);
    	text3.setPrefSize(w, g);
    	text4.setPrefSize(w, g);
    	text5.setPrefSize(w, g);
    	this.setXY(combox, 370, 20);
    	this.setXY(label6, 370, 5);
    	this.setXY(label1, 20, 10);
    	this.setXY(text1,20, 20);
    	this.setXY(label2, 20, 80);
    	this.setXY(text2, 20, 90);
    	this.setXY(label3, 20, 150);
    	this.setXY(text3, 20, 160);
    	this.setXY(label4,20, 220);
    	this.setXY(text4, 20, 230);
    	this.setXY(label5, 20, 290);
    	this.setXY(text5, 20, 300);
    	this.setXYWH(button2, 175,350, n, k);
    	this.setXYWH(button1, 285, 350, n, k);
    	root.getChildren().addAll(text1,text2,text3,text4,button1,button2,text5,label6);
    	root.getChildren().add(combox);
    	root.getChildren().addAll(label1,label2,label3,label4,label5);
    	Scene scene=new Scene(root,500,400);
    	stage.setScene(scene);
    	stage.show();
	}
	//第九视图的实现
	@SuppressWarnings("unchecked")
	public void displayTheNinthView(String code) {
		int n=100;//button长度
		int i=25;//button宽度
		int k=100;//每个column长度
		Stage ninthStage=new Stage();
		ninthStage.setTitle("个人云工厂管理");
		Pane root=new Pane();
		Button button1=new Button("租用设备");
		Button button2=new Button("开/关机");
		Button button3=new Button("新建");
		Button button4=new Button("删除");
		Button button5=new Button("重置");
		Button button6=new Button("修改");
		Button button7=new Button("退出账号");
		Button button8=new Button("归还设备");
		button1.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		button2.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		button3.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		button4.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		button5.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		button6.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		button7.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		button8.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		ObservableList<Equipment> data=FXCollections.observableArrayList(uController.ShowAllUserEquipment(filename, code));
		TableView<Equipment> tableView=new TableView<Equipment>(); 
		TableColumn<Equipment, Integer> IdColumn=new TableColumn<>("ID");
		TableColumn<Equipment, String> nameColumn=new TableColumn<>("设备名称");
		TableColumn<Equipment, String> codeColumn=new TableColumn<>("设备编号");
		TableColumn<Equipment, String> catagoryColumn=new TableColumn<>("设备类别");
		TableColumn<Equipment, String> sizeColumn=new TableColumn<>("设备规格");
		TableColumn<Equipment, String> netural1Column=new TableColumn<>("设备状态");
		TableColumn<Equipment, String> desColumn= new TableColumn<>("设备描述");
		TableColumn<Equipment,String> netural2Column=new TableColumn<>("租用状态");
		TableColumn<Equipment,String> belongColumn=new TableColumn<>("所属工厂");
		/////////////////////////////////////////////////////////////////////////////
		this.setXY(tableView, 25, 100);
		this.setXYWH(button3, 25, 65, n, i);
		this.setXYWH(button5, 140, 65, n, i);
		this.setXYWH(button4, 260, 65, n, i);
		this.setXYWH(button1, 715, 65, n, i);
		this.setXYWH(button2, 825, 65, n, i);
		this.setXYWH(button6, 370, 65, n, i);
		this.setXYWH(button7, 825, 560, n, i);
		this.setXYWH(button8, 610, 65, n, i);
		IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		catagoryColumn.setCellValueFactory(new PropertyValueFactory<>("catagory"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
		netural1Column.setCellValueFactory(new PropertyValueFactory<>("netural1"));
		desColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		netural2Column.setCellValueFactory(new PropertyValueFactory<>("netural2"));
		belongColumn.setCellValueFactory(new PropertyValueFactory<>("belong"));
		IdColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		codeColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		nameColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		catagoryColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		sizeColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		desColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		netural1Column.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		netural2Column.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		belongColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
        Image backgroundImage = new Image("file:images/10.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
//        backgroundImageView.setOpacity(0.6);
        this.setWH(backgroundImageView, 950,600);
        root.getChildren().add(backgroundImageView);
		IdColumn.setPrefWidth(k);codeColumn.setPrefWidth(k);nameColumn.setPrefWidth(k);
		catagoryColumn.setPrefWidth(k);sizeColumn.setPrefWidth(k);netural1Column.setPrefWidth(k);
		desColumn.setPrefWidth(k);netural2Column.setPrefWidth(k);belongColumn.setPrefWidth(k);
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableView.setPrefSize(899, 450);
		tableView.setItems(data);
		tableView.getColumns().addAll(IdColumn,nameColumn,codeColumn,catagoryColumn,sizeColumn,netural1Column);
		tableView.getColumns().addAll(desColumn,netural2Column,belongColumn);
		button1.setOnAction(watch->{
			ninthStage.close();
			this.lendEquipmentView(code);
		});
		button2.setOnAction(turn->{
			try {
			ObservableList<Equipment>select=tableView.getSelectionModel().getSelectedItems();
			ArrayList<Equipment> selections=new ArrayList<Equipment>(select);
			uController.checkNull(selections);
			uController.turnOn_Off(filename, selections);
			this.HappyEnd3("开/关机");
			}
			catch(NothingContainException e) {
				this.ExceptionEnd(e);
			}
		});
		
		button3.setOnAction(add->{
			this.displayNewView9(code);
		});
		button4.setOnAction(remove->{
			ObservableList<Equipment>select=tableView.getSelectionModel().getSelectedItems();
			ArrayList<Equipment> removions=new ArrayList<Equipment>(select);
			try {
				uController.checkNull(removions);
				uController.checkPublicEquipment(removions);
				this.checkView9(removions);
			}
			catch(NomalException e) {
				this.ExceptionEnd("不能删除公有的设备喵~");
			}
			catch(NothingContainException e) {
				this.ExceptionEnd(e);
			}
		});
		button5.setOnAction(reset->{
			ObservableList<Equipment> dataZ=FXCollections.observableArrayList(uController.ShowAllUserEquipment(filename, code));
			tableView.setItems(dataZ);
		});
		button6.setOnAction(change->{
			ObservableList<Equipment>select=tableView.getSelectionModel().getSelectedItems();
			ArrayList<Equipment> removtions=new ArrayList<Equipment>(select);
			try {
				adController.checkNull(removtions);
				uController.checkPublicEquipment(removtions);
				this.changeView9(removtions);
			}
        	catch(NomalException e) {
        		this.ExceptionEnd("共有设备不能修改信息喵~");
        	}
			catch(NothingContainException e) {
				this.ExceptionEnd(e);
			}

			});
		button7.setOnAction(back->{
		ninthStage.close();
		this.StartView();
		});
		button8.setOnAction(returning->{
			ObservableList<Equipment>select=tableView.getSelectionModel().getSelectedItems();
			ArrayList<Equipment> list=new ArrayList<Equipment>(select);
			try {
				uController.returnEquipment(filename, list);
				this.HappyEnd3("归还");
			}	
			catch(NothingContainException e) {
				this.ExceptionEnd(e);
			}
			catch(NomalException e) {
				this.ExceptionEnd("自己的设备不用归还哒喵~");
			}

		});
		root.getChildren().add(button3);
		root.getChildren().add(button1);
		root.getChildren().add(button2);
		root.getChildren().add(button4);
		root.getChildren().add(button5);
		root.getChildren().add(button6);
		root.getChildren().add(button7);
		root.getChildren().add(tableView);
		root.getChildren().add(button8);
		Scene scene=new Scene(root,950,600);
		ninthStage.setScene(scene);
		ninthStage.show();
	}
	//第九试图配套的新建视图
    public void displayNewView9(String userName) {
		int n=100;//
    	int k=25;
    	int w=250;//文本框长度
    	int g=25;//文本框宽度
    	Stage stage=new Stage();
    	stage.setTitle("设备新建");
    	Pane root=new Pane();
    	Label label=new Label();
    	ArrayList<Catagory> catagorys=adController.ShowAllEquipmentCatagory(filename);
    	Iterator<Catagory> iterator=catagorys.iterator();
    	label.setText("设备注册");
    	Button button1=new Button("确定");  
    	Button button2=new Button("返回");
    	button1.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
    	button2.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");

    	root.requestFocus();
    	TextField text1=new TextField();    	
    	text1.setFocusTraversable(false);
    	TextField text2=new TextField();
    	text2.setFocusTraversable(false);
    	TextField text3=new TextField();
    	text3.setFocusTraversable(false);
    	TextField text4=new TextField(); 
    	text4.setFocusTraversable(false);
//    	TextField text5=new TextField();
//    	text5.setFocusTraversable(false);
    	ComboBox<String> combox=new ComboBox<String>();
        Image backgroundImage = new Image("file:images/77.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setOpacity(0.6);
        this.setWH(backgroundImageView, 500,400);
        root.getChildren().add(backgroundImageView);
    	while(iterator.hasNext()) {
    		Catagory catagory=iterator.next();
    		combox.getItems().add(catagory.getName());
    	}
    	combox.setPromptText("设备类别");
    	combox.setPrefSize(110, 25);
        button1.setOnAction(add->{
        	try {
        	String catagory= combox.getValue();
    		String code=text1.getText();
    		String name=text2.getText();
    		String size=text3.getText();
    		String des=text4.getText();
    		uController.addEquipment(filename, name, code, catagory, size, des, userName);
    		PauseTransition pause=new PauseTransition(Duration.seconds(1.5));
    		pause.setOnFinished(good->{
    			stage.close();
    		});
    		this.HappyEnd3("新建");
    		pause.play();
        	}
        	catch(SomethingNullException e) {
        		this.ExceptionEnd(e);
        	}
        	catch(SameCodeException e) {
        		this.ExceptionEnd(e);
        	}
        });
    	button2.setOnAction(back->{
    		stage.close();
    	});
    	text1.setPromptText("新设备的编号：");
    	text2.setPromptText("新设备的名称:");
    	text3.setPromptText("新设备的规格:");
    	text4.setPromptText("新设备的描述:");
    	text1.setPrefSize(w, g);
    	text2.setPrefSize(w, g);
    	text3.setPrefSize(w, g);
    	text4.setPrefSize(w, g);
//    	text5.setPrefSize(w, g);
    	this.setXY(combox, 370, 20);
    	this.setXY(text1,20, 20);
    	this.setXY(text2, 20, 90);
    	this.setXY(text3, 20, 160);
    	this.setXY(text4, 20, 230);
//    	this.setXY(text5, 20, 300);
    	this.setXYWH(button2, 175,350, n, k);
    	this.setXYWH(button1, 285, 350, n, k);
    	root.getChildren().addAll(text1,text2,text3,text4,button1,button2);
    	root.getChildren().add(combox);
    	Scene scene=new Scene(root,500,400);
    	stage.setScene(scene);
    	stage.show();
    }
    //第九视图配套的删除视图
    public void checkView9(ArrayList<Equipment>removions) {
		Stage stage =new Stage();
		Pane root=new Pane();
		Button button1=new Button("确认");
		Button button2=new Button("再想想");
		button1.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		button2.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		Label label=new Label();
		label.setText("请再次确认是否要删除哦>.<");
		label.setFont(new Font(14));
		label.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		this.setXY(label, 10, 25);
		this.setXYWH(button2, 20, 55, 65, 25);
		this.setXYWH(button1, 105, 55, 65,25);
		button1.setOnAction(remove->{
			try {
			uController.removeEquipment(filename, removions);
			PauseTransition pause=new PauseTransition(Duration.seconds(1.5));
			pause.setOnFinished(back->{
				stage.close();
			});
			this.HappyEnd3("删除");
			pause.play();
			}
			catch(NomalException e) {
				this.ExceptionEnd("不能删除公有的设备喵~");
			}
		});
		button2.setOnAction(back->{
			stage.close();
		});
		root.getChildren().add(button1);
		root.getChildren().add(button2);
		root.getChildren().add(label);
		Scene scene=new Scene(root,200,100);
		stage.setScene(scene);
		stage.show();
    }
    //租用界面
    @SuppressWarnings("unchecked")
	public void lendEquipmentView(String code) {
    	int k=100;//每个column长度
		Stage stage=new Stage();
		stage.setTitle("个人云工厂管理");
		Pane root=new Pane();
		Label label=new Label("设备租用中心");
		label.setStyle("-fx-text-fill: RGB(218,112,214);");
		Button button1=new Button("返回");
		Button button2=new Button("租用");
		Button button3=new Button("刷新设备状态");
		button1.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		button2.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		button3.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
		ObservableList<Equipment>data=FXCollections.observableArrayList(adController.showAllFreeEquipment(filename));
		TableView<Equipment> tableView=new TableView<Equipment>(); 
		TableColumn<Equipment, Integer> IdColumn=new TableColumn<>("ID");
		TableColumn<Equipment, String> nameColumn=new TableColumn<>("设备名称");
		TableColumn<Equipment, String> codeColumn=new TableColumn<>("设备编号");
		TableColumn<Equipment, String> catagoryColumn=new TableColumn<>("设备类别");
		TableColumn<Equipment, String> sizeColumn=new TableColumn<>("设备规格");
		TableColumn<Equipment, String> netural1Column=new TableColumn<>("设备状态");
		TableColumn<Equipment, String> desColumn= new TableColumn<>("设备描述");
		TableColumn<Equipment,String> netural2Column=new TableColumn<>("租用状态");
		TableColumn<Equipment,String> belongColumn=new TableColumn<>("所属工厂");
		IdColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		codeColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		nameColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		catagoryColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		sizeColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		desColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		netural1Column.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		netural2Column.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		belongColumn.setStyle("-fx-text-fill: RGB(65,105,225);"+"-fx-font-weight: bold;");
		IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		catagoryColumn.setCellValueFactory(new PropertyValueFactory<>("catagory"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
		netural1Column.setCellValueFactory(new PropertyValueFactory<>("netural1"));
		desColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		netural2Column.setCellValueFactory(new PropertyValueFactory<>("netural2"));
		belongColumn.setCellValueFactory(new PropertyValueFactory<>("belong"));
        Image backgroundImage = new Image("file:images/10.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
//        backgroundImageView.setOpacity(0.6);
        this.setWH(backgroundImageView, 950,600);
        root.getChildren().add(backgroundImageView);
		label.setFont(new Font(20));
		this.setXY(tableView, 20, 50);
		this.setXY(label, 400, 10);
		this.setXYWH(button1, 700, 550, 100, 25);
		this.setXYWH(button2, 810, 550, 100, 25);
		this.setXYWH(button3, 810, 20, 100, 25);
		button1.setOnAction(back->{
			stage.close();
			this.displayTheNinthView(code);
		});
		button2.setOnAction(lend->{
			try {
			ObservableList<Equipment>select=tableView.getSelectionModel().getSelectedItems();
			ArrayList<Equipment> lendions=new ArrayList<Equipment>(select);
			adController.lendEquipment(filename, lendions, code);
			this.HappyEnd3("租用");
			}
			catch(NothingContainException e) {
				this.ExceptionEnd(e);
			}
		});
		button3.setOnAction(reset->{
			ObservableList<Equipment> dataZ=FXCollections.observableArrayList(adController.showAllFreeEquipment(filename));
			tableView.setItems(dataZ);
		});
		IdColumn.setPrefWidth(k);codeColumn.setPrefWidth(k);nameColumn.setPrefWidth(k);
		catagoryColumn.setPrefWidth(k);sizeColumn.setPrefWidth(k);netural1Column.setPrefWidth(k);
		desColumn.setPrefWidth(k);netural2Column.setPrefWidth(k);belongColumn.setPrefWidth(k);
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableView.setPrefSize(900, 490);
		tableView.getColumns().addAll(IdColumn,nameColumn,codeColumn,catagoryColumn,sizeColumn,netural1Column);
		tableView.getColumns().addAll(desColumn,netural2Column,belongColumn);
		tableView.setItems(data);
		root.getChildren().add(tableView);
		root.getChildren().add(label);
		root.getChildren().add(button1);
		root.getChildren().add(button2);
		root.getChildren().add(button3);
		Scene scene=new Scene(root,950,600);
		stage.setScene(scene);
		stage.show();
    }
    //配套的修改视图9
	public void changeView9(ArrayList<Equipment>select) {
		int n=100;//
    	int k=25;
    	int w=250;//文本框长度
    	int g=25;//文本框宽度
    	Stage stage=new Stage();
    	stage.setTitle("设备新建");
    	Pane root=new Pane();
    	Label label1=new Label("编号");
    	Label label2=new Label("名称");
    	Label label3=new Label("规格");
    	Label label4=new Label("描述");
    	Label label5=new Label("所属工厂");
    	Label label6=new Label("设备类别");
		label1.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		label2.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		label3.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		label4.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		label5.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
		label6.setStyle("-fx-text-fill: RGB(255,128,0);"+"-fx-font-weight: bold;");
    	Equipment equipment=select.get(0);
    	ArrayList<Catagory> catagorys=adController.ShowAllEquipmentCatagory(filename);
    	Iterator<Catagory> iterator=catagorys.iterator();
    	Button button1=new Button("确定");  
    	Button button2=new Button("返回");
    	button2.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
    	button1.setStyle("-fx-text-fill: RGB(218,112,214);"+"-fx-font-weight: bold;");
    	root.requestFocus();
    	TextField text1=new TextField(equipment.getCode());    	
    	text1.setFocusTraversable(false);
    	text1.setDisable(true);
    	TextField text2=new TextField(equipment.getName());
    	text2.setFocusTraversable(false);
    	TextField text3=new TextField(equipment.getSize());
    	text3.setFocusTraversable(false);
    	TextField text4=new TextField(equipment.getDescription()); 
    	text4.setFocusTraversable(false);
    	TextField text5=new TextField(equipment.getBelong()); 
    	text5.setFocusTraversable(false);
    	text5.setDisable(true);
    	ComboBox<String> combox=new ComboBox<String>();
        Image backgroundImage = new Image("file:images/77.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setOpacity(0.6);
        this.setWH(backgroundImageView, 500,400);
        root.getChildren().add(backgroundImageView);
    	while(iterator.hasNext()) {
    		Catagory catagory=iterator.next();
    		combox.getItems().add(catagory.getName());
    	}
    	combox.setPromptText(equipment.getCatagory());
    	combox.setValue(equipment.getCatagory());
    	combox.setPrefSize(110, 25);
        button1.setOnAction(change->{
        	try {
        	String code=text1.getText();
        	String name=text2.getText();
        	String catagory=combox.getValue();
        	String size=text3.getText();
        	String des=text4.getText();
        	String belong=text5.getText();
        	uController.changeEquipment(filename, code, name, catagory, size, des, belong);
        	PauseTransition pause=new PauseTransition(Duration.seconds(1.5));
        	pause.setOnFinished(back->{
        		stage.close();
        	});
        	this.HappyEnd3("修改");
        	pause.play();
        	}
        	catch(SomethingNullException e) {
        		this.ExceptionEnd(e);
        	}
        });
    	button2.setOnAction(back->{
    		stage.close();
    	});
    	text1.setPromptText("设备的编号：");
    	text2.setPromptText("设备的名称:");
    	text3.setPromptText("设备的规格:");
    	text4.setPromptText("设备的描述:");
    	text5.setPromptText("设备的所属工厂");
    	text1.setPrefSize(w, g);
    	text2.setPrefSize(w, g);
    	text3.setPrefSize(w, g);
    	text4.setPrefSize(w, g);
    	text5.setPrefSize(w, g);
    	this.setXY(label6, 370, 5);
    	this.setXY(combox, 370, 20);
    	this.setXY(label1, 20, 10);
    	this.setXY(text1,20, 20);
    	this.setXY(label2, 20, 80);
    	this.setXY(text2, 20, 90);
    	this.setXY(label3, 20, 150);
    	this.setXY(text3, 20, 160);
    	this.setXY(label4,20, 220);
    	this.setXY(text4, 20, 230);
    	this.setXY(label5, 20, 290);
    	this.setXY(text5, 20, 300);
    	this.setXYWH(button2, 175,350, n, k);
    	this.setXYWH(button1, 285, 350, n, k);
    	root.getChildren().addAll(text1,text2,text3,text4,button1,button2,text5,label6);
    	root.getChildren().add(combox);
    	root.getChildren().addAll(label1,label2,label3,label4,label5);
    	Scene scene=new Scene(root,500,400);
    	stage.setScene(scene);
    	stage.show();
	}
    //异常视图,喵喵~
	public void ExceptionEnd (String contain) {
		Stage stage=new Stage();
		Pane root=new Pane();
		Label label=new Label();
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
        Image backgroundImage = new Image("file:images/55.png");
		root.setStyle("-fx-background-color: white;");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        this.setWH1(backgroundImageView, 110, 40,50, 50);
        this.setXY(label, 30, 20);
        root.getChildren().add(backgroundImageView);
		label.setFont(new Font(16));
		label.setText(contain);
		label.setStyle("-fx-text-fill: RGB(218,120,214);"+"-fx-font-weight: bold;");
		root.getChildren().add(label);
		Scene scene=new Scene(root,280,100);
		stage.setScene(scene);
		pause.setOnFinished(end->{
			stage.close();
		});
		stage.show();
		pause.play();
	}
	public void ExceptionEnd (NotFoundException e) {
		Stage stage=new Stage();
		Pane root=new Pane();
		Label label=new Label();
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
        Image backgroundImage = new Image("file:images/55.png");
		root.setStyle("-fx-background-color: white;");
		label.setStyle("-fx-text-fill: RGB(218,120,214);"+"-fx-font-weight: bold;");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        this.setWH1(backgroundImageView, 110, 40,50, 50);
        this.setXY(label, 90, 20);
        root.getChildren().add(backgroundImageView);
		label.setFont(new Font(16));
		label.setText("没有找到喵~");
		root.getChildren().add(label);
		Scene scene=new Scene(root,280,100);
		stage.setScene(scene);
		pause.setOnFinished(end->{
			stage.close();
		});
		stage.show();
		pause.play();
	}
	public void ExceptionEnd (WasQuotedException e) {
		Stage stage=new Stage();
		Pane root=new Pane();
		Label label=new Label();
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		label.setStyle("-fx-text-fill: RGB(218,120,214);"+"-fx-font-weight: bold;");
        Image backgroundImage = new Image("file:images/55.png");
		root.setStyle("-fx-background-color: white;");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        this.setWH1(backgroundImageView, 110, 40,50, 50);
        this.setXY(label, 20, 20);
        root.getChildren().add(backgroundImageView);
		label.setFont(new Font(16));
		label.setText("这个类别正在被引用，不能删除喵~");
		root.getChildren().add(label);
		Scene scene=new Scene(root,280,100);
		stage.setScene(scene);
		pause.setOnFinished(end->{
			stage.close();
		});
		stage.show();
		pause.play();
	}
	public void ExceptionEnd (SameCodeException e) {
		Stage stage=new Stage();
		Pane root=new Pane();
		Label label=new Label();
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
        Image backgroundImage = new Image("file:images/55.png");
		label.setStyle("-fx-text-fill: RGB(218,120,214);"+"-fx-font-weight: bold;");
		root.setStyle("-fx-background-color: white;");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        this.setWH1(backgroundImageView, 110, 40,50, 50);
        this.setXY(label, 50, 20);
        root.getChildren().add(backgroundImageView);
		label.setFont(new Font(16));
		label.setText("编号重复了喵~,换个编号哦");
		root.getChildren().add(label);
		Scene scene=new Scene(root,280,100);
		stage.setScene(scene);
		pause.setOnFinished(end->{
			stage.close();
		});
		stage.show();
		pause.play();
	}	
	public void ExceptionEnd (SomethingNullException e) {
		Stage stage=new Stage();
		Pane root=new Pane();
		Label label=new Label();
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
        Image backgroundImage = new Image("file:images/55.png");
		label.setStyle("-fx-text-fill: RGB(218,120,214);"+"-fx-font-weight: bold;");
		root.setStyle("-fx-background-color: white;");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        this.setWH1(backgroundImageView, 110, 40,50, 50);
        this.setXY(label, 50, 20);
        root.getChildren().add(backgroundImageView);
		label.setFont(new Font(16));
		label.setText("填写的信息不能有空的喵~");
		root.getChildren().add(label);
		Scene scene=new Scene(root,280,100);
		stage.setScene(scene);
		pause.setOnFinished(end->{
			stage.close();
		});
		stage.show();
		pause.play();
	}	
	public void ExceptionEnd (NothingContainException e) {
		Stage stage=new Stage();
		Pane root=new Pane();
		Label label=new Label();
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
        Image backgroundImage = new Image("file:images/55.png");
		label.setStyle("-fx-text-fill: RGB(218,120,214);"+"-fx-font-weight: bold;");
		root.setStyle("-fx-background-color: white;");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        this.setWH1(backgroundImageView, 110, 40,50, 50);
        this.setXY(label, 50, 20);
        root.getChildren().add(backgroundImageView);
		label.setFont(new Font(16));
		label.setText("还什么都没有选中呢喵~");
		root.getChildren().add(label);
		Scene scene=new Scene(root,280,100);
		stage.setScene(scene);
		pause.setOnFinished(end->{
			stage.close();
		});
		stage.show();
		pause.play();
	}
	public void ExceptionEnd (MultipleSelectException e) {
		Stage stage=new Stage();
		Pane root=new Pane();
		Label label=new Label();
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
        Image backgroundImage = new Image("file:images/55.png");
		label.setStyle("-fx-text-fill: RGB(218,120,214);"+"-fx-font-weight: bold;");
		root.setStyle("-fx-background-color: white;");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        this.setWH1(backgroundImageView, 110, 40,50, 50);
        this.setXY(label, 12, 20);
        root.getChildren().add(backgroundImageView);
		label.setFont(new Font(16));
		label.setText("不能一次修改多个哦，太贪心了喵~");
		root.getChildren().add(label);
		Scene scene=new Scene(root,280,100);
		stage.setScene(scene);
		pause.setOnFinished(end->{
			stage.close();
		});
		stage.show();
		pause.play();
	}
	//第八视图的配套修改视图
//	public void changeView8(ArrayList<Equipment> select) {
//    	Stage stage=new Stage();
//    	Pane root=new Pane();
//    	ComboBox <String>combobox =new ComboBox<>();
//    	combobox.getItems().addAll("产品编号","产品名称","产品规格","产品描述","产品类别");
//    	combobox.setPromptText("想要修改的内容");
//    	Label label=new Label();
//    	label.setText("");
//    	label.setPrefWidth(200);
//    	Button button1=new Button("确定");
//    	Button button2=new Button("返回");
//    	button1.setOnAction(change->{ 		
//    		if(combobox.getValue().equals("产品类别")) {
//    			this.changView8C(select);
//    		}
//    		else {
//    			this.changeView8B(combobox.getValue(), select);
//    		}
//    		stage.close();
//    	});
//    	button2.setOnAction(back->{
//    		stage.close();
//    	});
//    	this.setXYWH(button2,70, 75, 80,25);
//    	this.setXYWH(button1,185, 75, 80,25);
//    	label.setFont(new Font(15));
//    	Text text=new Text(" 您  要  修  改  的  内  容  是:");
//    	text.setFont(label.getFont());
//   	    text.setLineSpacing(10);
//    	label.setGraphic(text);
//    	this.setXY(label, 10, 35);
//    	this.setXY(combobox, 215,35);
//    	root.getChildren().add(combobox);
//    	root.getChildren().add(label);
//    	root.getChildren().add(button2);
//    	root.getChildren().add(button1);
//    	Scene scene=new Scene(root,350,100);
//    	stage.setScene(scene);
//    	stage.show();
//	}
	///
//	public void changeView8B(String Z,ArrayList<Equipment> select) {
//    	Stage stage=new Stage();
//    	Pane root=new Pane();
//    	TextField textField=new TextField();
//    	Button button1=new Button("返回");
//    	Button button2=new Button("确定");
//    	textField.setPromptText("请输入新的"+Z);
//    	textField.setFocusTraversable(false);
//    	this.setXY(textField, 45, 35);
//    	textField.setPrefSize(200, 25);
//    	this.setXYWH(button1, 45, 65,85,25);
//    	button1.setOnAction(back->{
//    		stage.close();
//    	});
//    	button2.setOnAction(change->{
////    		adController.(select,"C:\\Users\\80510\\Desktop\\Database.dat",Z,textField.getText());
//    		PauseTransition pause=new PauseTransition(Duration.seconds(1.5));
//    		pause.setOnFinished(back->{
//    			stage.close();
//    		});
//    		this.HappyEnd3("修改");
//    		pause.play();
//    	});
//    	this.setXYWH(button2, 140,65,85,25);
//    	Scene scene=new Scene(root,300,100);
//    	root.getChildren().add(textField);
//    	root.getChildren().add(button2);
//    	root.getChildren().add(button1);
//    	stage.setScene(scene);
//    	stage.show();
//	}
}