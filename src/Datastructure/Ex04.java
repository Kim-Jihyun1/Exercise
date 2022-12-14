package Datastructure;

import java.util.*;

public class Ex04 {
    public static void main(String[] args) {
        Integer[] boxes = new Integer[]{1, 3, 4, 2, 1, 6};
        int output = paveBox(boxes);
        System.out.println(output); // 3

        Integer[] boxes2 = new Integer[]{1, 5, 7, 9};
        int output2 = paveBox(boxes2);
        System.out.println(output2); // 1
    }

    public static int paveBox(Integer[] boxes) {
        /*
        1. 자신을 기준으로 뒷 사람의 박스 개수를 파악
        2. 자신보다 박스의 개수가 적은 사람의 수를 카운트 -> 한 번에 나갈 수 있는 사람의 수
        3. count 의 값을 비교해 가장 큰 값을 리턴
        ** 각 요소를 기준으로 count -> boxes 배열의 크기와 동일한 count 배열을 선언
         */
        //ToDo :
        ArrayList<Integer> result = new ArrayList<>(); //정답을 담을 리스트 선언
        List<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(boxes)); //box를 순회 -> 리스트로 변경 (array to list)

        //box 를 모두 순회 -> arrayList
        while (arrayList.size() > 0) { //arrayList 가 비어 있게 될 때까지 순회
            //자신(앞 사람)과 뒷 사람들을 비교
            for(int i = 0; i < arrayList.size(); i++) {
                //자신과 비교했을 때, 자신보다 작은 요소들과 함께 탈출
                if(arrayList.get(i) > arrayList.get(0)) {
                    result.add(i);
                    arrayList = arrayList.subList(i , arrayList.size()); //나간 요소들 제거
                    break; //더 큰 요소를 찾은 경우 탈출
                }

                //모든 요소가 나갈 수 있는 경우 (자신보다 큰 요소를 찾을 수 없는 경우)
                if(i == arrayList.size() - 1) {
                    result.add(arrayList.size());
                    arrayList.clear();
                }
            }
        }
        return result.stream().max(Integer::compare).orElse(-1);

//        Queue<Integer> q = new LinkedList<>();
//
//        int[] count = new int[boxes.length]; //count의 값을 비교하여 최댓값을 리턴 -> count를 배열로 저장
//
//        for (int i = 0; i < count.length; i++) {
//            count[i] = 1; //자신도 포함되어야 하므로 1로 초기화
//        }
//
//        //배열을 큐애 하나식 push
//        //각각의 요소를 기준으로 비교하므로 큐에 add 할 때 큐의 크기를 하나씩 줄임
//        for (int i = 0; i < boxes.length - 1; i++) { //비교
//            q.clear(); //반복하면서 큐에 값이 저장되지 않게 초기화
//
//            //인덱스를 하나씩 늘리면서 큐에 배열을 저장 -> 비교할 요소를 변경
//            for (int j = i; j < boxes.length; j++) {
//                q.add(boxes[j]);
//                System.out.println(q);
//
//                int first = q.poll();
//
//                for (int k = 0; k < q.size(); k++) { //first 를 기준으로 비교
//                    if (first >= q.poll()) count[i]++;
//                    else break;
//                }
//            }
//        }
//        //count 에 저장된 수들을 비교하여 최댓값을 리턴
//        int max = count[0];
//
//        for (int i = 1; i < count.length; i++) {
//            if (max < count[i]) max = count[i];
//        }
//        return max;
    }
}