import java.awt.Frame;

/**
 * chat聊天程序
 * 先做一个面板对话框
 * @author jinpeng.chen
 * @date  2013-4-8
 */
public class ChatClient extends Frame {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new ChatClient("Chat Client").launchFrame();
	}

	public ChatClient(String name) {
		super(name);
	}

	public void launchFrame() {
		setVisible(true);
		setBounds(200, 200, 400, 300);
	}
}
