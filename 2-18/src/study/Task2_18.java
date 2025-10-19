package study;
    public class Task2_18 {
 
   
    //問② Personクラスをインスタンス化し、名前「山田太郎」 年齢「23」 住所「東京都」 をカプセル化されたsetterで設定して下さい。
        public static void main(String[] args) {
            Person person = new Person(); 
            person.setName("山田太郎");
            person.setAge(23);
            person.setAddress("東京都");
            //問③ 設定した名前・年齢・住所をカプセル化されたgetterで呼び出してして下さい。
            System.out.println(person.getName());
            System.out.println(person.getAge());
            System.out.println(person.getAddress());

        }
    }
  
class Person {
    private String name;
    private int age;
    private String address;
    //問① 上記変数に従って、getterとsetterを設定して下さい。
   
    public String getName() {
      return this.name;
    }
    public void setName(String name) {
       this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
     }
    public String getAddress() {
            return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
            
    }
 }

        
        
        