package schedule.skeleton;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import c4j.ReportTimeCallCpp;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import schedule.io.BigLittleConverter;
import schedule.io.ReadFromLocal;
import schedule.model.TimeTable;
import schedule.viewmodel.StationVM;
import schedule.viewmodel.TimeTableVM;

public class ReportTimeController implements Initializable {

	@FXML
	private ComboBox<String> inOrOut;

	@FXML
	private Button cancelReport;

	@FXML
	private Button reportTimeBtn;

	@FXML
	private Label trainNum;

	@FXML
	private ComboBox<String> stationName;

	@FXML
	private TextField reportTime;

	private String oldTime;
	public static int msgCount = 1;
	public static int msgCount1 = 1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		inOrOut.getItems().add("接车");
		inOrOut.getItems().add("发车");

		for (StationVM stationVm : TimeTableVM.allStationVM) {
			stationName.getItems().add(stationVm.getStationName());
		}

		cancelReport.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				Stage stage = (Stage) cancelReport.getScene().getWindow();
				stage.close();
			}
		});
		reportTimeBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				try {
					String server0 = ReadFromLocal.getPath(3);
					String port0 = ReadFromLocal.getPath(4);
					String server1 = ReadFromLocal.getPath(5);
					String port1 = ReadFromLocal.getPath(6);
					String server2 = ReadFromLocal.getPath(7);
					String port2 = ReadFromLocal.getPath(8);

					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								// System.out.println(ReportTimeCallCpp.myadd(1, 2));

								DatagramSocket datagramSocket = new DatagramSocket();

								// 头
								byte[] data0 = BigLittleConverter.toMinByte((char) 0xBE);
								byte[] data1 = BigLittleConverter.toMinByte((short) 0);
								byte[] data2 = BigLittleConverter.toMinByte((char) 1);
								byte[] data3 = BigLittleConverter.toMinByte((char) 3);
								byte[] data4 = BigLittleConverter.toMinByte((short) 1);
								byte[] data5 = BigLittleConverter.toMinByte((char) 81);
								byte[] data6 = BigLittleConverter.toMinByte((char) 3);
								byte[] data7 = BigLittleConverter.toMinByte((short) 1);
								byte[] data8 = BigLittleConverter.toMinByte((char) 93);
								byte[] data9 = BigLittleConverter.toMinByte((short) Short.parseShort(oldTime.split(" ")[0].split("-")[0]));
								byte[] data10 = BigLittleConverter.toMinByte((char) Short.parseShort(oldTime.split(" ")[0].split("-")[1]));
								byte[] data11 = BigLittleConverter.toMinByte((char) Short.parseShort(oldTime.split(" ")[0].split("-")[2]));
								byte[] data12 = BigLittleConverter.toMinByte(
										(int) ((LocalTime.now().getHour() * 60 + LocalTime.now().getMinute()) * 60
												+ LocalTime.now().getSecond()) * 1000);
								byte[] data13 = BigLittleConverter.toMinByte((int) msgCount++);
								byte[] data14 = BigLittleConverter.toMinByte((char) 0);
								byte[] data15 = BigLittleConverter.toMinByte((char) 0);
								byte[] data16 = BigLittleConverter.toMinByte((char) 1);
								byte[] data17 = BigLittleConverter.toMinByte((short) 1);
								byte[] data18 = BigLittleConverter.toMinByte((int) 0);
								byte[] data19 = BigLittleConverter.toMinByte((char) 0);

								// 信息字1
								byte[] bh = BigLittleConverter.toMinByte((short) 4259);
								byte[] cllx = BigLittleConverter.toMinByte((char) 2);
								byte[] glcbh = BigLittleConverter.toMinByte((char) 1);
								byte[] by = BigLittleConverter.toMinByte((short) 0);

								// 包1 站点信息
								byte[] xh = BigLittleConverter.toMinByte((char) msgCount1++);
								byte[] jcz = BigLittleConverter.toMinByte((char) 0xAA);
								byte[] y = BigLittleConverter.toMinByte((char) Integer.parseInt(oldTime.split(" ")[0].split("-")[1]));
								byte[] n = BigLittleConverter.toMinByte((char) Integer.parseInt(oldTime.split(" ")[0].split("-")[0]));
								byte[] s = BigLittleConverter.toMinByte((char) Integer.parseInt(oldTime.split(" ")[1].split(":")[0]));
								byte[] r = BigLittleConverter.toMinByte((char) Integer.parseInt(oldTime.split(" ")[0].split("-")[2]));
								byte[] m = BigLittleConverter.toMinByte((char) Integer.parseInt(oldTime.split(" ")[1].split(":")[2]));
								byte[] f = BigLittleConverter.toMinByte((char) Integer.parseInt(oldTime.split(" ")[1].split(":")[1]));

								String strName = stationName.getValue();
								byte[] name = strName.getBytes();
								byte[] cd = BigLittleConverter.toMinByte((char) name.length);

								byte[] head = BigLittleConverter.concatBytes(data0, data1, data2, data3, data4, data5, data6, data7, data8,
										data9, data10, data11, data12, data13, data14, data15, data16, data17, data18,
										data19, bh, cllx, glcbh, by, xh, jcz, y, n, s, r, m, f, cd, name);

								// 修改长度
								byte[] totalLen = BigLittleConverter.toMinByte((short) (head.length));
								if (totalLen.length == 2) {
									head[1] = totalLen[0];
									head[2] = totalLen[1];
								}
								// 添加校验
								byte jyh = 0;
								for (int i = 0; i < head.length; i++) {
									if (i != 33) {
										jyh += head[i];
									}
								}
								head[33] = jyh;

								InetAddress address0 = InetAddress.getByName(server0);
								DatagramPacket datagramPacket0 = new DatagramPacket(head, head.length, address0,
										Integer.parseInt(port0));
								
								InetAddress address1 = InetAddress.getByName(server1);
								DatagramPacket datagramPacket1 = new DatagramPacket(head, head.length, address1,
										Integer.parseInt(port1));
								
								InetAddress address2 = InetAddress.getByName(server2);
								DatagramPacket datagramPacket2 = new DatagramPacket(head, head.length, address2,
										Integer.parseInt(port2));
								
								datagramSocket.send(datagramPacket0);
								datagramSocket.send(datagramPacket1);
								datagramSocket.send(datagramPacket2);

								datagramSocket.close();

								Platform.runLater(() -> {
									((MainController) (Main.loader.getController())).refresh(
											trainNum.getText().split(" ")[0], oldTime.split(" ")[1], reportTime.getText(),
											inOrOut.getValue());
									oldTime = reportTime.getText();
									
									Stage stage = (Stage) cancelReport.getScene().getWindow();
									stage.close();
								});
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}).start();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		stationName.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				if (inOrOut.getValue() != null) {
					String time = TimeTableVM.searchTrainState(trainNum.getText().split(" ")[0], stationName.getValue(),
							inOrOut.getValue());
					reportTime.setText(time.split(" ")[1]);
					oldTime = time;
				}
			}
		});

		inOrOut.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				if (stationName.getValue() != null) {
					String time = TimeTableVM.searchTrainState(trainNum.getText().split(" ")[0], stationName.getValue(),
							inOrOut.getValue());
					reportTime.setText(time.split(" ")[1]);
					oldTime = time;
				}
			}
		});
	}

	public void setData(List<SimpleStringProperty> list) {
		// TODO Auto-generated method stub
		trainNum.setText(list.get(0).getValue() + " 次列车于");

		stationName.setValue(stationName.getItems().get(0));
		inOrOut.setValue(inOrOut.getItems().get(1));
	}
}
