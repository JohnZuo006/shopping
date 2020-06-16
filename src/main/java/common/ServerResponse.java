package common;

/**
 * @author zuoxinlei
 * @version 鍒涘缓鏃堕棿锛�2020骞�6鏈�16鏃� 涓嬪崍3:07:13 绫昏鏄�
 */
public class ServerResponse<T> {
	private int status;
	private T data;
	private String msg;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "ServerResponse [status=" + status + ", data=" + data + ", msg=" + msg + "]";
	}
	
}
