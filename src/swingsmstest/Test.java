package swingsmstest;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class Test extends JFrame{
	private JButton btn1,btn2;
	private JLabel la1,la2;
	private JTextField tf1,tf2;
	private Container c;
	private GridLayout grid;
	
	public Test() {
		setTitle("CoolSms 문자보내기");
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c=getContentPane();
		grid=new GridLayout(3,2);
		grid.setVgap(5);
		
		c.setLayout(grid);
		la1=new JLabel("<html>전화번호 <br> ex) 01012341234 </html>");
		la2=new JLabel("메시지");
		tf1=new JTextField();
		tf2=new JTextField();
		btn1=new JButton("전송");
		btn2=new JButton("초기화");
		
		la1.setHorizontalAlignment(JLabel.CENTER);
		la2.setHorizontalAlignment(JLabel.CENTER);

		c.add(la1);
		c.add(tf1);
		c.add(la2);
		c.add(tf2);
		c.add(btn1);
		c.add(btn2);
		
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				문자전송(tf1.getText(),tf2.getText());
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tf1.setText("");
				tf2.setText("");
			}
		});
		
		setVisible(true);
	}
	static public void 문자전송(String to, String text) {
		String api_key = "NCSULUKFMKKW2UNT";
	    String api_secret = "NVPUOJ6ZAWHXMUPAB3PNCHVCMU2RSN6Z";
	    Message coolsms = new Message(api_key, api_secret);

	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", to);
	    params.put("from", "01087941860");
	    params.put("type", "SMS");
	    params.put("text", text);
	    params.put("app_version", "test app 1.2");
	    
	    try {
	      JSONObject obj = (JSONObject) coolsms.send(params);
	      System.out.println("메시지가 전송되었습니다.");
	      System.out.println(obj.toString());
	    } catch (CoolsmsException e) {
	      System.out.println(e.getMessage());
	      System.out.println(e.getCode());
	    }
	  }
	public static void main(String[] args) {
		new Test();
	}
}
