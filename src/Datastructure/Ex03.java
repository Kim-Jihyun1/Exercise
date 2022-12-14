package Datastructure;

import java.util.*;

public class Ex03 {
    public static void main(String[] args) {
        String[] actions = new String[]{"B", "C", "-1", "D", "A", "-1", "1", "-1", "-1"};
        String start = "A";
        ArrayList<Stack> output = browserStack(actions, start);

        System.out.println(output); // [["A"], ["B"], ["A", "D"]]

        String[] actions2 = new String[]{"B", "-1", "B", "A", "C", "-1", "-1", "D", "-1", "1", "E", "-1", "-1", "1"};
        String start2 = "A";
        ArrayList<Stack> output2 = browserStack(actions2, start2);

        System.out.println(output2); // [["A", "B"], ["D"], ["E"]]
    }
    public static ArrayList<Stack> browserStack(String[] actions, String start) {
        Stack<String> prevStack = new Stack<>(); //이전 페이지
        Stack<String> nextStack = new Stack<>(); //다음 페이지
        Stack<String> current = new Stack<>(); //현재 페이지
        ArrayList<Stack> result = new ArrayList<>();

        //ToDo :
        //start = 시작 페이지, actions : 명령어

        current.push(start); //current 에 시작 페이지를 넣어 줌

        for (int i = 0; i < actions.length; i++) { //actions 배열 순회

            //뒤로 가기 버튼을 누른 경우 "-1" && prevStack 이 비어있지 않은 경우
            if (actions[i].equals("-1") && !prevStack.isEmpty()) { //문자열이므로 equals() 사용

                //현재 페이지를 nextStack 에 보관(push)
                nextStack.push(current.pop());

                //prevStack 에서 가장 나중에 보관된 페이지(top)를 현재 페이지로 가져옴
                current.push(prevStack.pop());
            }

            //앞으로 가기 버튼을 누른 경우 "1" && nextStack 이 비어있지 않은 경우
            else if (actions[i].equals("1") && !nextStack.isEmpty()) {
                prevStack.push(current.pop());
                current.push(nextStack.pop());
            }

            //앞으로 가기, 뒤로 가기 버튼이 비활성화 된 경우 아무것도 하지 않아야 함
            else if(actions[i].equals("1") || actions[i].equals("-1")){}

            //새로운 페이지로 접속한 경우
            else {
                //현재 페이지를 prevStack 에 보관
                prevStack.push(current.pop());

                //next 비우기
                current.push(actions[i]);

                //nextStack 에 저장된 모든 데이터를 삭제하고 스택 초기화
                nextStack.clear();
            }
        }
        //모든 스택을 result 에 저장한 후 반환
        result.add(prevStack);
        result.add(current);
        result.add(nextStack);

        return result;
    }
}
