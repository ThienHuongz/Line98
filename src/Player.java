package Lines;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Player {
   public String name;
   public int scores;

   public Player() {
      this.name = "";
      this.scores = 0;
   }

   public Player(String var1, int var2) {
      this.name = var1;
      this.scores = var2;
   }

   public void setName() {
      String var1 = JOptionPane.showInputDialog(" Ten cua ban la gi vay ?") + "";
      this.name = var1;
   }

   public void writeData(DataOutput var1) throws IOException {
      this.writeFixedString(this.name, 20, var1);
      var1.writeInt(this.scores);
   }

   public void readData(DataInput var1) throws IOException {
      this.name = this.readFixedString(20, var1);
      this.scores = var1.readInt();
   }

   public String readFixedString(int var1, DataInput var2) throws IOException {
      StringBuilder var3 = new StringBuilder(var1);
      int var4 = 0;
      boolean var5 = true;

      while(var5 && var4 < var1) {
         char var6 = var2.readChar();
         ++var4;
         if (var6 == 0) {
            var5 = false;
         } else {
            var3.append(var6);
         }
      }

      var2.skipBytes(2 * (var1 - var4));
      return var3.toString();
   }

   public void writeFixedString(String var1, int var2, DataOutput var3) throws IOException {
      for(int var4 = 0; var4 < var2; ++var4) {
         char var5 = 0;
         if (var4 < var1.length()) {
            var5 = var1.charAt(var4);
         }

         var3.writeChar(var5);
      }

   }
}
