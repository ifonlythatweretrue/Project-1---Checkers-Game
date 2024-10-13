
import java.util.Scanner;

public class Checkers {
    static byte turn = 0;
    public static void CapturePiece(int[] board, int opponent){
        board[(turn+1) %2] = Utility.clearBit(board[turn + 1 % 2],opponent);
    }
    public static void multiJump(int pos,boolean king, byte turn,int[] board){
        int[] direction;
        if (king){
             direction = new int[]{-9,-7,9,7};//potential king jump moves
        }
        else {
            if (turn == 1){//pawn multi jump option
                 direction = new int[]{-9,-7};
            }
            else {
                direction = new int[]{9,7};
            }
        }
        for (int jump: direction){//iterate through the possible second jump moves then recursive calls again for triple then quadruple jump
            int endpos = pos + jump;
            if (IsLegalMove(board,pos,endpos)){//protocol for setting a legal p=moves
                if (31 >= endpos && endpos >= 28 && turn == 0 || 0 <= endpos && endpos < 4 && turn == 1){//king promo
                    board[2] = Utility.toggleBit(board[2],endpos);
                }
                board[turn] = Utility.setBit(board[turn], endpos);
                board[turn] = Utility.clearBit(board[turn], pos);
                if (Utility.getBit(board[2],pos)){//check for king new position on the king bitboard
                    board[2] = Utility.setBit(board[turn], endpos);
                    board[2] = Utility.clearBit(board[turn], pos);
                }
                    multiJump(endpos,Utility.getBit(board[2],endpos),turn, board);

            return;
            }

        }
    }
   public static void MovePiece(int position, int end, int[]board){
      if (IsLegalMove(board,position,end)) {
          if (31 >= end && end >= 28 && turn == 0 || 0 <= end && end < 4 && turn == 1){//king promo
            board[2] = Utility.toggleBit(board[2],end);
          }
              board[turn] = Utility.setBit(board[turn], end);//set the new board state for legal move
              board[turn] = Utility.clearBit(board[turn], position);//clearing previous position
              if (Utility.getBit(board[2],position)){//check for king new position on the king bitboard
                  board[2] = Utility.setBit(board[turn], end);
                  board[2] = Utility.clearBit(board[turn], position);
              }
              if (Math.abs(end - position) > 5){//check for multi-jump trick
                  multiJump(end,Utility.getBit(board[2],end),turn, board);
              }
              turn = (byte) (1 - turn);//change turns
      }
      else {
          System.out.println("Not a legal move for your turn");
      }
   }
    public static boolean IsLegalMove(int[]board,int position,int end) {
        if (end < 0 || end > 31) {
            return false; // Invalid move due to boundary check
        }
        int add = Utility.Addition(board[0], board[1]);//adding occupied square of light and dark together
        int empty = ~add;//empty square flip to ones
        int opponent = board[(turn + 1) % 2];// calculating capture of occupied square


        if (Utility.getBit(board[turn],position)) {//check if it's the player piece

            if (turn == 0 || Utility.getBit(board[2],position)) {//check the king to move also
                if ((position  / 4 +1) % 2 == 0 && Utility.getBit(empty,end)) {//pattern for even and odd rows
                    if ((end == position + 4 || end == position + 3)) {//potential diagonal moves
                        return true;
                    } //conditional edge case
                    else if (position % 4 == 0 && end == position + 4 ) {//checking for boundary edge of the right side
                        return true;
                    }//left capture
                    else if ((position+1) % 4 != 0 && end == position + 9 && Utility.getBit(opponent,position + 4)) {//see if we can capture
                        if (Utility.getBit(board[2],opponent)){
                            if (Utility.getBit(board[2],position)){
                                board[2] = Utility.toggleBit(board[2],opponent);//demote the king
                                return true;
                            }
                            System.out.println("Cannot capture King using pawn");
                            return false;
                        }

                        CapturePiece(board,position +4);
                        return true;
                    }//right capture
                    else if (position % 4 != 0 && end == (position + 7) && Utility.getBit(opponent,position + 3)) {//see if we can capture
                        if (Utility.getBit(board[2],opponent)){
                            if (Utility.getBit(board[2],position)){
                                board[2] = Utility.toggleBit(board[2],opponent);//demote the king
                                return true;


                            }
                            System.out.println("Cannot capture King using pawn");
                            return false;
                        }


                        CapturePiece(board,position +3);
                        return true;
                    }
                }
                else {//odd row for diagonal moves and checking for empty squares
                    if (end == position + 5 || end == position + 4 && Utility.getBit(empty,end)) {
                        return true;
                    } else if (position + 1 % 4 == 0 && end == position + 4 && Utility.getBit(empty,end)) {//boundary edge of the left side
                        return true;
                    }//left capture
                    else if ((position) % 4 != 0 && end == position + 9 && Utility.getBit(opponent,position + 4)) {//see if we can capture
                        if (Utility.getBit(board[2],opponent)){
                            if (Utility.getBit(board[2],position)){
                                board[2] = Utility.toggleBit(board[2],opponent);//demote the king

                                return true;
                            }
                            System.out.println("Cannot capture King using pawn");
                            return false;
                        }


                        CapturePiece(board,position +4);
                        return true;
                    }//right capture
                    else if ((position+1) % 4 != 0 && end == position + 7 && Utility.getBit(opponent,position + 3)) {//see if we can capture
                        if (Utility.getBit(board[2],opponent)){
                            if (Utility.getBit(board[2],position)){
                                board[2] = Utility.toggleBit(board[2],opponent);//demote the king

                                return true;
                            }
                            System.out.println("Cannot capture King using pawn");
                            return false;
                        }

                        CapturePiece(board,position +3);
                        return true;
                    }

                }
            }
             if (turn == 1|| Utility.getBit(board[2],position)) {//can go backward or forward depend on player moves
                //left move
                if ((position / 4) % 2 == 1) {
                    if (end == position - 4 || end == position - 5 && Utility.getBit(empty,end)) { //downward board moves for diagonal
                        return true;
                    }//right  move
                 else if (position % 4 == 0 && end == position - 4 && Utility.getBit(empty,end)) {//special edge case for downward checker moves
                    return true;
                }//left capture
                else if ((position+1) % 4 != 0 && end == position - 7 && Utility.getBit(opponent,position - 4)) {//see if we can capture
                        if (Utility.getBit(board[2],opponent)){
                            if (Utility.getBit(board[2],position)){
                                board[2] = Utility.toggleBit(board[2],opponent);//demote the king

                                return true;
                            }
                            System.out.println("Cannot capture King using pawn");
                            return false;
                        }


                        CapturePiece(board,position - 4);
                    return true;
                }//right capture
                else if (position % 4 != 0 && end == position - 9 && Utility.getBit(opponent,position - 5)) {//see if we can capture
                    if (Utility.getBit(board[2],opponent)){
                        if (Utility.getBit(board[2],position)){
                            board[2] = Utility.toggleBit(board[2],opponent);//demote the king

                            return true;
                        }
                        System.out.println("Cannot capture King using pawn");
                        return false;
                    }

                    CapturePiece(board,position -5);
                    return true;
                }
                }
                else {
                    if (end == position - 4 || end == position - 3 && Utility.getBit(empty,end)) {// odd rows special case for diagonal moves
                        return true;
                    } else if ((position + 1) % 4 == 0 && end == position - 4 && Utility.getBit(empty,end)) {// checking the left side boundary case legal moves
                        return true;
                    }
                    //left capture
                    else if ((position+1) % 4 != 0 && end == position - 7 && Utility.getBit(opponent,position - 3)) {//see if we can capture
                        if (Utility.getBit(board[2],opponent)){
                            if (Utility.getBit(board[2],position)){
                                board[2] = Utility.toggleBit(board[2],opponent);//demote the king

                                return true;
                            }
                            System.out.println("Cannot capture King using pawn");
                            return false;
                        }


                        CapturePiece(board,position -3);
                        return true;
                    }//right capture
                    else if ((position) % 4 != 0 && end == position - 9 && Utility.getBit(opponent,position - 4)) {//see if we can capture
                        if (Utility.getBit(board[2],opponent)){
                            if (Utility.getBit(board[2],position)){
                                board[2] = Utility.toggleBit(board[2],opponent);//demote the king

                                return true;
                            }
                            System.out.println("Cannot capture King using pawn");
                            return false;
                        }


                        CapturePiece(board,position -4);
                        return true;
                    }

                }
            }
        }
   return false; }
    public static void InitializeBoard(int[] board){

        for (int i = 0; i < 12 ; i++){
            board[0] = Utility.setBit(board[0],i);
        }
        for (int i =20; i < 32 ; i++){
            board[1] = Utility.setBit(board[1],i);
        }
    }
    public static void PrintBoard(int[] board){
        int count = 31;
    for (int i = 0; i < 8; i++){
        if (i%2 == 0){
            System.out.printf("%-" + 4 + "s", "."); // Empty square showing the count
        }
        for (int j = 0; j < 4; j++){
            if (Utility.getBit(board[0],count)){
                if (Utility.getBit(board[2],count)){
                    System.out.printf("%-" + 4 + "s", "0K");
                }
                else {
                    System.out.printf("%-" + 4 + "s", "0"); // turn 0 piece
                }
            }
            else if (Utility.getBit(board[1],count)){
                if (Utility.getBit(board[2],count)){
                    System.out.printf("%-" + 4 + "s", "XK");
                }
                else {
                    System.out.printf("%-" + 4 + "s", "X"); // turn 1 piece
                }
            }
            else {
                System.out.printf("%-" + 4 + "s", count); // Empty square showing the count
            }
            if (j != 3 || i%2 !=0) System.out.printf("%-" + 4 + "s", "."); // Empty square showing the count
            count--;
        }
        System.out.println();

    }
    }
    public static boolean UpdateGameState(int[] board){
        if (board[0] == 0){//no pieces for player 0
            System.out.println("Player 1 wins!");
            return false;
        }
        else if (board[1] == 0){//no pieces for player 1
            System.out.println("Player 0 wins!");
            return false;
        }// checks non-capturing or capturing legal moves board pieces and players' kings on the board
       else if ((board[0] << 4 & ~(Utility.Addition(board[0],board[1]))) == 0 || (board[1] >> 4 & ~(Utility.Addition(board[0],board[1]))) == 0 && (board[2] & board[turn]) != 0){
            System.out.println("draw/stalemate, no more legal move");
           return false;
        }

    return true;}
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] board = new int[3]; // bitboard for light dark pieces as well king promotion
        InitializeBoard(board);

        while (UpdateGameState(board)){//update the state of the game if it is a win or not
            PrintBoard(board);//creating the board in ascii
            System.out.println("Turn " + turn);
            System.out.println("Play: position & move");
          String[] s =scanner.nextLine().split(" ");
        MovePiece(Integer.parseInt(s[0]),Integer.parseInt(s[1]),board);

        }
        //turn based board game
        //11 15 23 19 7 11 22 18 2 7 26 23 8 12 31 26 15 22


    }
}

