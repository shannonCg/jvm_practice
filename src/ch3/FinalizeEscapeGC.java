package ch3;

import java.util.Objects;

/**
 *  此測試證明：
 *  1. 對象可在被GC時，透過finalize()再度被其他對象引用，即可避免被GC掉
 *  2. 但每個對象的finalize()只會被jvm呼叫一次
 *  3. 但官方強烈建議不要改寫finalize()來做任何事情，因為jvm呼叫finalize()的時間不確定，且也不保證會等待finalize()執行完成
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("yes, i am still alive :)");
    }

    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable {
        SAVE_HOOK = new FinalizeEscapeGC();

        //對象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        // 因為Finalizer方法優先級很低，暫停0.5秒，以等待它
        Thread.sleep(500);
        if(Objects.nonNull(SAVE_HOOK)){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no, i am dead :(");
        }

        //下面程式碼和上面完全相同，但這次自救卻失敗了
        SAVE_HOOK = null;
        System.gc();
        // 因為Finalizer方法優先級很低，暫停0.5秒，以等待它
        Thread.sleep(500);
        if(Objects.nonNull(SAVE_HOOK)){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no, i am dead :(");
        }
    }
}