package learnThread;


class Name{
	private String test = "abc";

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
}

class Account
{
    /* ����һ��ThreadLocal���͵ı������ñ�������һ���ֲ߳̾�����
    ÿ���̶߳��ᱣ���ñ�����һ������ */
    private ThreadLocal<Name> name = new ThreadLocal<>();
    // ����һ����ʼ��name��Ա�����Ĺ�����
    public Account(Name str)
    {
        this.name.set(str);
        // ����������ڷ��ʵ�ǰ�̵߳�name������ֵ
        System.out.println("---" + this.name.get());
    }
    // name��setter��getter����
    public Name getName()
    {
    	System.out.println(name.get().getTest());
        return name.get();
    }
    public void setName(Name str)
    {
        this.name.set(str);
    }
}
class MyTest extends Thread
{
    // ����һ��Account���͵ĳ�Ա����
    private Account account;
    public MyTest(Account account, String name)
    {
        super(name);
        this.account = account;
        System.out.println(this.account);
        System.out.println(this.account.getName());
        System.out.println(this.account.getName().getTest());
    }
    public void run()
    {
    	account.getName();
        // ѭ��10��
        for (int i = 0 ; i < 5 ; i++)
        {
        	System.out.println(getName()+i);
            // ��i == 6ʱ������˻����滻�ɵ�ǰ�߳���
            if (i == 2)
            {
                account.getName().setTest(getName());
            }
            // ���ͬһ���˻����˻�����ѭ������
//            System.out.println(this.account);
//            System.out.println(this.account.getName());
//            System.out.println(getName()+"------"+ this.account.getName().getTest()
//                + " �˻���iֵ��" + i);
        }
    }
}
public class ThreadLocalTest
{
    public static void main(String[] args)
    {
        // ���������̣߳������̹߳���ͬһ��Account
    	Name name = new Name();
        Account at = new Account(name);
        at.getName();
        /*
        ��Ȼ�����̹߳���ͬһ���˻�����ֻ��һ���˻���
        �������˻�����ThreadLocal���͵ģ�����ÿ���߳�
        ����ȫӵ�и��Ե��˻������������Դ�i == 6֮�󣬽���������
        �̷߳���ͬһ���˻�ʱ������ͬ���˻�����
        */
        new MyTest(at , "�̼߳�").start();
//        new MyTest(at , "�߳���").start ();
    }
}
