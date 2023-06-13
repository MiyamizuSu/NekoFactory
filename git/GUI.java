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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
		TextField text1=new TextField();
		TextField text2=new TextField();
		Image background=new Image("C:\\Users\\80510\\Desktop\\顶级\\qq_pic_merged_1666005398742.jpg");
		BackgroundImage backIm=new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,new BackgroundSize(800, 600, false, false, true, true));
		text1.setPromptText("账号:");
		text2.setPromptText("密码:");
		button1.setText("登录");
		button2.setText("注册");
		button1.setOnAction(event -> {
			String name=text1.getText();
			String key=text2.getText();
			if(adController.Log_in(name, key, 1,"C:\\Users\\80510\\Desktop\\Database.dat"))
			{
				this.displayHappyEnd("登陆成功");
				PauseTransition pause=new PauseTransition(Duration.seconds(1));
				pause.setOnFinished(event1 ->{
					firstStage.close();
				});
				pause.play();
			}
			else if(uController.Log_in(name, key,"C:\\Users\\80510\\Desktop\\Database.dat"))
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
		root.setBackground(new Background(backIm));
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
		int n=80;//行间距
		int mid=20;//初始y坐标
		int i=150;//text初始x
		int k= 300;//text长度
		Stage sceondStage =new Stage();
		Pane root=new Pane();
		Button button1=new Button("确定");Button button2=new Button("返回");
		Label la1=new Label();Label la2=new Label();Label la3=new Label();
		Label la4=new Label();Label la5=new Label();Label la6=new Label();Label la7=new Label();
		TextField text1=new TextField();TextField text2=new TextField();TextField text3=new TextField();
		TextField text4=new TextField();TextField text5=new TextField();TextField text6=new TextField();
		ToggleGroup raButtons =new ToggleGroup();
		RadioButton raButton1=new RadioButton("云工厂"); RadioButton raButton2=new RadioButton("经销商");
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
            uController.registered("C:\\Users\\80510\\Desktop\\Database.dat",code,key, owenerName, ownerTel,OwnerZ,name,des);	
            this.displayHappyEnding("注册成功");
			PauseTransition pause = new PauseTransition(Duration.seconds(3));
			pause.setOnFinished(event1 ->{
			sceondStage.close();
			});
			pause.play();
			}	
				catch(SomethingNullException e)
				{
				this.ExceptionEnd("填写的内容不能有空哦");}
			catch(SameCodeException e) {
				this.ExceptionEnd("账号重复了喵~,换个账号名吧");
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
		this.setXYWH(button1, 235, 600, 75,50);this.setXYWH(button2, 400, 600, 75,50);
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
	    Button button1=new Button("用户管理");Button button2=new Button("云工厂管理");Button button3=new Button("产品类别管理");
	    Button button4=new Button("产品信息管理");Button button5=new Button("设备类型管理"); Button button6=new Button("设备管理");
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
		Image image=new Image("C:\\Users\\80510\\Desktop\\11.png");
		ImageView imView=new ImageView(image);
		Image image1=new Image("C:\\Users\\80510\\Desktop\\22.png");
		ImageView imView1=new ImageView(image1);
		TableView<User> tableView=new TableView<>();
		ObservableList<User> data=FXCollections.observableArrayList(adController.showAllUser("C:\\Users\\80510\\Desktop\\Database.dat"));
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
			User ans=adController.GetUser("C:\\Users\\80510\\Desktop\\Database.dat",name);
			ObservableList<User> dataZ=FXCollections.observableArrayList();
			dataZ.add(ans);
			textField.clear();
			tableView.setItems(dataZ);
			}
			catch(NotFoundException e) {
				this.ExceptionEnd("没有找到喵~");
			}
		});
		button2.setOnAction(reset->{
			ObservableList<User> dataZ=FXCollections.observableArrayList(adController.showAllUser("C:\\Users\\80510\\Desktop\\Database.dat"));
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
		Label label=new Label();
		label.setText("请再次确认是否要删除哦>.<");
		label.setFont(new Font(14));
		this.setXY(label, 10, 25);
		this.setXYWH(button2, 20, 55, 65, 25);
		this.setXYWH(button1, 105, 55, 65,25);

		button1.setOnAction(remove->{
			adController.removeUser("C:\\Users\\80510\\Desktop\\Database.dat",removions);
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
	//成功界面
	public void HappyEnd3 (String contain) {
		Stage stage=new Stage();
		StackPane root=new StackPane();
		Label label=new Label();
		PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
		label.setFont(new Font(16));
		label.setText(contain+"成功啦!");
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
		int n=80;//行间距
		int mid=20;//初始y坐标
		int i=150;//text初始x
		int k= 300;//text长度
		Stage sceondStage =new Stage();
		Pane root=new Pane();
		Button button1=new Button("确定");Button button2=new Button("返回");
		Label la1=new Label();Label la2=new Label();Label la3=new Label();
		Label la4=new Label();Label la5=new Label();Label la6=new Label();Label la7=new Label();
		TextField text1=new TextField();TextField text2=new TextField();TextField text3=new TextField();
		TextField text4=new TextField();TextField text5=new TextField();TextField text6=new TextField();
		ToggleGroup raButtons =new ToggleGroup();
		RadioButton raButton1=new RadioButton("云工厂"); RadioButton raButton2=new RadioButton("经销商");
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
            uController.registered("C:\\Users\\80510\\Desktop\\Database.dat", code,key, owenerName, ownerTel,OwnerZ,name,des);
            this.HappyEnd3("注册");
			PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
			pause.setOnFinished(event1 ->{
			sceondStage.close();
			});
			pause.play();
			}
			catch(SomethingNullException e) {
				this.ExceptionEnd("填写的内容不能有空哦");
			}
			catch(SameCodeException e) {
				this.ExceptionEnd("账号重复了喵~,换个账号名吧");
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
		this.setXYWH(button1, 235, 600, 75,50);this.setXYWH(button2, 400, 600, 75,50);
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
		int n=80;//行间距
		int mid=20;//初始y坐标
		int i=150;//text初始x
		int k= 300;//text长度
		Stage sceondStage =new Stage();
		Pane root=new Pane();
		User ans=select.get(0);
		Button button1=new Button("确定");Button button2=new Button("返回");
		Label la1=new Label();Label la2=new Label();Label la3=new Label();
		Label la4=new Label();Label la5=new Label();Label la6=new Label();Label la7=new Label();
		TextField text1=new TextField(ans.getCode());TextField text2=new TextField(ans.getKey());TextField text3=new TextField(ans.getName());
		TextField text4=new TextField(ans.getTel());TextField text5=new TextField(ans.getFaname());TextField text6=new TextField(ans.getFaDes());
		RadioButton raButton1=new RadioButton("云工厂"); RadioButton raButton2=new RadioButton("经销商");
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
			adController.changeMessage("C:\\Users\\80510\\Desktop\\Database.dat",text2.getText(),text3.getText(),text4.getText(), text5.getText(),text6.getText(),select);
			PauseTransition pause=new PauseTransition(Duration.seconds(1.5));
			pause.setOnFinished(back->{
				sceondStage.close();
			});
			this.HappyEnd3("修改");
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
		this.setXYWH(button1, 235, 600, 75,50);this.setXYWH(button2, 400, 600, 75,50);
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
		TableView<Factory> tableView= new TableView<Factory>();
		ObservableList<Factory> data= FXCollections.observableArrayList(adController.showAllFactory("C:\\Users\\80510\\Desktop\\Database.dat"));
		TableColumn<Factory, Integer> IDColumn=new TableColumn<>("ID");
		TableColumn<Factory, String> nameColumn=new TableColumn<>("工厂名称");
		TableColumn<Factory, String> ownerColumn=new TableColumn<>("负责人");
		TableColumn<Factory, String> telColumn=new TableColumn<>("联系方式");
		TableColumn<Factory, String> codeColumn=new TableColumn<>("登陆账号");
		TableColumn<Factory, String> netrualColunmn=new TableColumn<>("工厂状态");
		Image image=new Image("C:\\Users\\80510\\Desktop\\11.png");
		ImageView imView=new ImageView(image);
		Image image1=new Image("C:\\Users\\80510\\Desktop\\22.png");
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
			Factory ans=adController.getFactory("C:\\Users\\80510\\Desktop\\Database.dat",name);
			ObservableList<Factory> dataZ=FXCollections.observableArrayList();
			dataZ.add(ans);
			textField.clear();
			tableView.setItems(dataZ);
			}
			catch(NotFoundException e) {
				this.ExceptionEnd("没有找到喵");
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
            adController.changeNetural(select,"C:\\Users\\80510\\Desktop\\Database.dat");
			ObservableList<Factory> dataZ=FXCollections.observableArrayList(adController.showAllFactory("C:\\Users\\80510\\Desktop\\Database.dat"));
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
		TableView<Catagory> tableView=new TableView<Catagory>();
		ObservableList<Catagory> data= FXCollections.observableArrayList(adController.showAllProductCatagory("C:\\Users\\80510\\Desktop\\Database.dat"));
		Button button1=new Button("按类别名称查询");
		Button button2=new Button("重置");
		Button button3=new Button("新建");
		Button button4=new Button("删除");
		Button button5=new Button("返回");
		TextField textField=new TextField();
		Image image=new Image("C:\\Users\\80510\\Desktop\\11.png");
		ImageView imView=new ImageView(image);
		Image image1=new Image("C:\\Users\\80510\\Desktop\\22.png");
		ImageView imView1=new ImageView(image1);
		root.setStyle("-fx-background-color: white;");
		textField.setPromptText("在此输入名字");
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		button1.setOnAction(research->{
			try {
			String name=textField.getText();
			Catagory ans=adController.getACatagory("C:\\Users\\80510\\Desktop\\Database.dat", name);
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
			ObservableList<Catagory>dataZ=FXCollections.observableArrayList(adController.showAllProductCatagory("C:\\Users\\80510\\Desktop\\Database.dat"));
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
		label.setText("请在下方输入新产品类的名称");
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
			adController.addCatagory("C:\\Users\\80510\\Desktop\\Database.dat",textField.getText());
			stage.close();
			this.HappyEnd3("新建");
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
		Label label=new Label();
		label.setText("请再次确认是否要删除哦>.<");
		label.setFont(new Font(14));
		this.setXY(label, 10, 25);
		this.setXYWH(button2, 20, 55, 65, 25);
		this.setXYWH(button1, 105, 55, 65,25);
		button1.setOnAction(remove->{
			try {
			adController.removeProductCatagory(removions,"C:\\Users\\80510\\Desktop\\Database.dat");
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
		Image image=new Image("C:\\Users\\80510\\Desktop\\11.png");
		ImageView imView=new ImageView(image);
		Image image1=new Image("C:\\Users\\80510\\Desktop\\22.png");
		ImageView imView1=new ImageView(image1);
		textField.setPromptText("在此输入类别:");
		textField.setFocusTraversable(false);
		button2.requestFocus();
		TableView <Product>tableView=new TableView<Product>();
		tableView.setFocusTraversable(false);
		ObservableList<Product> data=FXCollections.observableArrayList(adController.showAllProduct("C:\\Users\\80510\\Desktop\\Database.dat"));
		TableColumn<Product, Integer> IdColumn=new TableColumn<>("ID");
		TableColumn<Product, String> codeColumn=new TableColumn<>("产品编号");
		TableColumn<Product, String> nameColumn=new TableColumn<>("产品名称");
		TableColumn<Product, String> catagoryColumn=new TableColumn<>("产品类别");
		TableColumn<Product, String> sizeColumn=new TableColumn<>("产品规格");
 		TableColumn<Product, String> desColumn=new TableColumn<>("产品描述");
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
			ArrayList<Product> ans=adController.getProduct("C:\\Users\\80510\\Desktop\\Database.dat", catagoryName);
			ObservableList<Product> DataZ=FXCollections.observableArrayList(ans);
			tableView.setItems(DataZ);
			}
			catch(NotFoundException e) {
				this.ExceptionEnd(e);
			}
		});
		button2.setOnAction(reset->{
			ObservableList<Product> DataZ=FXCollections.observableArrayList(adController.showAllProduct("C:\\Users\\80510\\Desktop\\Database.dat"));
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
    	int w=280;//文本框长度
    	int g=25;//文本框宽度
    	Stage stage=new Stage();
    	stage.setTitle("产品新建");
    	Pane root=new Pane();
    	Label label=new Label();
    	ArrayList<Catagory> catagorys=adController.showAllProductCatagory("C:\\Users\\80510\\Desktop\\Database.dat");
    	Iterator<Catagory> iterator=catagorys.iterator();
    	label.setText("产品注册");
    	Button button1=new Button("确定");  
    	Button button2=new Button("返回");
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
    		adController.addNewProduct("C:\\Users\\80510\\Desktop\\Database.dat", name, catagory, code, size, des);
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
    	this.setXY(combox, 350, 40);
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
		Label label=new Label();
		label.setText("请再次确认是否要删除哦>.<");
		label.setFont(new Font(14));
		this.setXY(label, 10, 25);
		this.setXYWH(button2, 20, 55, 65, 25);
		this.setXYWH(button1, 105, 55, 65,25);
		button1.setOnAction(remove->{
			adController.removeProduct("C:\\Users\\80510\\Desktop\\Database.dat", removions);
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
    	int w=280;//文本框长度
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
    	ArrayList<Catagory> catagorys=adController.showAllProductCatagory("C:\\Users\\80510\\Desktop\\Database.dat");
    	Iterator<Catagory> iterator=catagorys.iterator();
    	Button button1=new Button("确定");  
    	Button button2=new Button("返回");
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
    	combox.setPrefSize(110, 25);
        button1.setOnAction(add->{try {
        	String name=text2.getText();
        	String size=text3.getText();
        	String des=text4.getText();
        	String Catagory=combox.getValue();
        	adController.changeProduct(select,"C:\\Users\\80510\\Desktop\\Database.dat", name, Catagory, size, des);
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
    	this.setXY(combox, 350, 40);
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
		sixthStage.setTitle("产品类别管理");
		Pane root =new Pane();
		TableColumn<Catagory, Integer> IDColumn=new TableColumn<>("ID");
		TableColumn<Catagory, String> catagoryColumn=new TableColumn<>("类别名称");
		TableView<Catagory> tableView=new TableView<Catagory>();
		ObservableList<Catagory> data= FXCollections.observableArrayList(adController.ShowAllEquipmentCatagory("C:\\Users\\80510\\Desktop\\Database.dat"));
		Button button1=new Button("按类别名称查询");
		Button button2=new Button("重置");
		Button button3=new Button("新建");
		Button button4=new Button("删除");
		Button button5=new Button("返回");
		TextField textField=new TextField();
		Image image=new Image("C:\\Users\\80510\\Desktop\\11.png");
		ImageView imView=new ImageView(image);
		Image image1=new Image("C:\\Users\\80510\\Desktop\\22.png");
		ImageView imView1=new ImageView(image1);
		root.setStyle("-fx-background-color: white;");
		textField.setPromptText("在此输入名字");
		tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		button1.setOnAction(research->{
			try {
			String name=textField.getText();
			Catagory ans=adController.getAEquipmentCatagory("C:\\Users\\80510\\Desktop\\Database.dat",name);
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
			ObservableList<Catagory>dataZ=FXCollections.observableArrayList(adController.ShowAllEquipmentCatagory("C:\\Users\\80510\\Desktop\\Database.dat"));
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
		stage.setTitle("新建产品类");
		Label label=new Label();
		TextField textField=new TextField();
		Button button1=new Button("返回");
		Button button2=new Button("确定");
		textField.setPromptText("在此输入名称:");
		textField.setPrefSize(200, 40);
		textField.setStyle("-fx-font-size: 16px;");
		label.setText("请在下方输入新产品类的名称");
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
			adController.addEquipmentCatagory("C:\\Users\\80510\\Desktop\\Database.dat",textField.getText());
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
		Label label=new Label();
		label.setText("请再次确认是否要删除哦>.<");
		label.setFont(new Font(14));
		this.setXY(label, 10, 25);
		this.setXYWH(button2, 20, 55, 65, 25);
		this.setXYWH(button1, 105, 55, 65,25);
		button1.setOnAction(remove->{
			try {
			adController.removeEquipmentCatagory(removions,"C:\\Users\\80510\\Desktop\\Database.dat");
			stage.close();
			this.HappyEnd3("删除");
			}
			catch(WasQuotedException e) {
				stage.close();
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
		ObservableList<Equipment> data=FXCollections.observableArrayList(adController.showAllEquipments("C:\\Users\\80510\\Desktop\\Database.dat"));
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
		Image image=new Image("C:\\Users\\80510\\Desktop\\sign.gif");
		ImageView imView=new ImageView(image);
		Image image1=new Image("C:\\Users\\80510\\Desktop\\22.png");
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
			ObservableList<Equipment> dataZ=FXCollections.observableArrayList(adController.getEquipment("C:\\Users\\80510\\Desktop\\Database.dat",catagoryName));
			tableView.setItems(dataZ);
			}
			catch(NotFoundException e) {
				this.ExceptionEnd(e);
			}
		});
		button2.setOnAction(reset->{
			ObservableList<Equipment> dataZ=FXCollections.observableArrayList(adController.showAllEquipments("C:\\Users\\80510\\Desktop\\Database.dat"));
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
			adController.turnOn_Off("C:\\Users\\80510\\Desktop\\Database.dat", selections);
			this.HappyEnd3("开/关机");
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
		this.setXY(tableView, 10, 180);
		this.setXY(imView1, 450, 0);
		this.setWH(imView1, 200, 180);
		this.setWH1(imView, 650, 0, 400,180);
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
		Scene scene=new Scene(root,950,600);
		eightthStage.setScene(scene);
		eightthStage.show();
	}
	//与第八视图配套的新建视图
	public void addView8A() {
		int n=100;//
    	int k=25;
    	int w=280;//文本框长度
    	int g=25;//文本框宽度
    	Stage stage=new Stage();
    	stage.setTitle("设备新建");
    	Pane root=new Pane();
    	Label label=new Label();
    	ArrayList<Catagory> catagorys=adController.ShowAllEquipmentCatagory("C:\\Users\\80510\\Desktop\\Database.dat");
    	Iterator<Catagory> iterator=catagorys.iterator();
    	label.setText("设备注册");
    	Button button1=new Button("确定");  
    	Button button2=new Button("返回");
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
    		adController.addNewEquipment("C:\\Users\\80510\\Desktop\\Database.dat", name,code,catagory,size,des);
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
    	this.setXY(combox, 350, 20);
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
		Label label=new Label();
		label.setText("请再次确认是否要删除哦>.<");
		label.setFont(new Font(14));
		this.setXY(label, 10, 25);
		this.setXYWH(button2, 20, 55, 65, 25);
		this.setXYWH(button1, 105, 55, 65,25);
		button1.setOnAction(remove->{
			adController.removeEquipment(removions,"C:\\Users\\80510\\Desktop\\Database.dat");
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
    	int w=280;//文本框长度
    	int g=25;//文本框宽度
    	Stage stage=new Stage();
    	stage.setTitle("设备新建");
    	Pane root=new Pane();
    	Label label1=new Label("编号");
    	Label label2=new Label("名称");
    	Label label3=new Label("规格");
    	Label label4=new Label("描述");
    	Label label5=new Label("所属工厂");
    	Equipment equipment=select.get(0);
    	ArrayList<Catagory> catagorys=adController.ShowAllEquipmentCatagory("C:\\Users\\80510\\Desktop\\Database.dat");
    	Iterator<Catagory> iterator=catagorys.iterator();
    	Button button1=new Button("确定");  
    	Button button2=new Button("返回");
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
        	adController.changeEquipment("C:\\Users\\80510\\Desktop\\Database.dat", code, name, catagory, size, des, belong);
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
    	this.setXY(combox, 350, 20);
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
    	root.getChildren().addAll(text1,text2,text3,text4,button1,button2,text5);
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
		ObservableList<Equipment> data=FXCollections.observableArrayList(uController.ShowAllUserEquipment("C:\\Users\\80510\\Desktop\\Database.dat", code));
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
		tableView.setPrefSize(899, 450);
		tableView.setItems(data);
		tableView.getColumns().addAll(IdColumn,nameColumn,codeColumn,catagoryColumn,sizeColumn,netural1Column);
		tableView.getColumns().addAll(desColumn,netural2Column,belongColumn);
		button1.setOnAction(watch->{
			this.lendEquipmentView(code);
		});
		button2.setOnAction(turn->{
			try {
			ObservableList<Equipment>select=tableView.getSelectionModel().getSelectedItems();
			ArrayList<Equipment> selections=new ArrayList<Equipment>(select);
			uController.checkNull(selections);
			uController.turnOn_Off("C:\\Users\\80510\\Desktop\\Database.dat", selections);
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
				this.checkView9(removions);
			}
			catch(NothingContainException e) {
				this.ExceptionEnd(e);
			}
		});
		button5.setOnAction(reset->{
			ObservableList<Equipment> dataZ=FXCollections.observableArrayList(uController.ShowAllUserEquipment("C:\\Users\\80510\\Desktop\\Database.dat", code));
			tableView.setItems(dataZ);
		});
		button6.setOnAction(change->{
			ObservableList<Equipment>select=tableView.getSelectionModel().getSelectedItems();
			ArrayList<Equipment> removtions=new ArrayList<Equipment>(select);
			try {
				adController.checkNull(removtions);
				uController.checkMultiple(removtions);
				this.changeView9(removtions);
			}
			catch(MultipleSelectException e) {
				this.ExceptionEnd(e);
			}
			catch(NothingContainException e) {
				this.ExceptionEnd(e);
			}

			});
		button7.setOnAction(back->{
		ninthStage.close();
		this.StartView();
		});
		root.getChildren().add(button3);
		root.getChildren().add(button1);
		root.getChildren().add(button2);
		root.getChildren().add(button4);
		root.getChildren().add(button5);
		root.getChildren().add(button6);
		root.getChildren().add(button7);
		root.getChildren().add(tableView);
		Scene scene=new Scene(root,950,600);
		ninthStage.setScene(scene);
		ninthStage.show();
	}
	//第九试图配套的新建视图
    public void displayNewView9(String userName) {
		int n=100;//
    	int k=25;
    	int w=280;//文本框长度
    	int g=25;//文本框宽度
    	Stage stage=new Stage();
    	stage.setTitle("设备新建");
    	Pane root=new Pane();
    	Label label=new Label();
    	ArrayList<Catagory> catagorys=adController.ShowAllEquipmentCatagory("C:\\Users\\80510\\Desktop\\Database.dat");
    	Iterator<Catagory> iterator=catagorys.iterator();
    	label.setText("设备注册");
    	Button button1=new Button("确定");  
    	Button button2=new Button("返回");
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
    		uController.addEquipment("C:\\Users\\80510\\Desktop\\Database.dat", name, code, catagory, size, des, userName);
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
    	this.setXY(combox, 350, 20);
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
		Label label=new Label();
		label.setText("请再次确认是否要删除哦>.<");
		label.setFont(new Font(14));
		this.setXY(label, 10, 25);
		this.setXYWH(button2, 20, 55, 65, 25);
		this.setXYWH(button1, 105, 55, 65,25);
		button1.setOnAction(remove->{
			try {
			uController.removeEquipment("C:\\Users\\80510\\Desktop\\Database.dat", removions);
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
		Button button1=new Button("返回");
		Button button2=new Button("租用");
		Button button3=new Button("刷新设备状态");
		Button button4=new Button("修改");
		ObservableList<Equipment>data=FXCollections.observableArrayList(adController.showAllFreeEquipment("C:\\Users\\80510\\Desktop\\Database.dat"));
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
		IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
		catagoryColumn.setCellValueFactory(new PropertyValueFactory<>("catagory"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
		netural1Column.setCellValueFactory(new PropertyValueFactory<>("netural1"));
		desColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		netural2Column.setCellValueFactory(new PropertyValueFactory<>("netural2"));
		belongColumn.setCellValueFactory(new PropertyValueFactory<>("belong"));
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
			adController.lendEquipment("C:\\Users\\80510\\Desktop\\Database.dat", lendions, code);
			this.HappyEnd3("租用");
			}
			catch(NothingContainException e) {
				this.ExceptionEnd(e);
			}
		});
		button3.setOnAction(reset->{
			ObservableList<Equipment> dataZ=FXCollections.observableArrayList(adController.showAllFreeEquipment("C:\\Users\\80510\\Desktop\\Database.dat"));
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
    	int w=280;//文本框长度
    	int g=25;//文本框宽度
    	Stage stage=new Stage();
    	stage.setTitle("设备新建");
    	Pane root=new Pane();
    	Label label1=new Label("编号");
    	Label label2=new Label("名称");
    	Label label3=new Label("规格");
    	Label label4=new Label("描述");
    	Label label5=new Label("所属工厂");
    	Equipment equipment=select.get(0);
    	ArrayList<Catagory> catagorys=adController.ShowAllEquipmentCatagory("C:\\Users\\80510\\Desktop\\Database.dat");
    	Iterator<Catagory> iterator=catagorys.iterator();
    	Button button1=new Button("确定");  
    	Button button2=new Button("返回");
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
    	combox.setPrefSize(110, 25);
        button1.setOnAction(change->{
        	try {
        	String code=text1.getText();
        	String name=text2.getText();
        	String catagory=combox.getValue();
        	String size=text3.getText();
        	String des=text4.getText();
        	String belong=text5.getText();
        	uController.changeEquipment("C:\\Users\\80510\\Desktop\\Database.dat", code, name, catagory, size, des, belong);
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
    	this.setXY(combox, 350, 20);
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
    	root.getChildren().addAll(text1,text2,text3,text4,button1,button2,text5);
    	root.getChildren().add(combox);
    	root.getChildren().addAll(label1,label2,label3,label4,label5);
    	Scene scene=new Scene(root,500,400);
    	stage.setScene(scene);
    	stage.show();
	}
    //异常视图,喵喵~
	public void ExceptionEnd (String contain) {
		Stage stage=new Stage();
		StackPane root=new StackPane();
		Label label=new Label();
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		label.setFont(new Font(16));
		label.setText(contain);
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
		StackPane root=new StackPane();
		Label label=new Label();
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
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
		StackPane root=new StackPane();
		Label label=new Label();
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
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
		StackPane root=new StackPane();
		Label label=new Label();
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
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
		StackPane root=new StackPane();
		Label label=new Label();
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
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
		StackPane root=new StackPane();
		Label label=new Label();
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
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
		StackPane root=new StackPane();
		Label label=new Label();
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
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