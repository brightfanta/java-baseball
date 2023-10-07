package baseball;


import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        //난수 생성
        List<Integer> computer = new ArrayList<>();
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }

        String userInput = "";
        //3스트라이크 될 때까지 반복해야 함
        //사용자가 잘못된 값을 입력할 경우 예외 발생시키고 종료
        int repetition = 1;
        while (repetition == 1) {
            try {
                userInput = Console.readLine();
            } catch (Exception e) {
                //IllegalArgumentException
            }


        }


        int inputTemp = 0;
        String inputStringTemp = "";
        inputTemp = Integer.parseInt(userInput);

        List<Integer> userInputList = new ArrayList<>();
        separateInputNum(userInput, userInputList);


        int strikeCnt = 0;
        int ballCnt = 0;

        //반복문 동시 추출
        //while next() method 활용
        /*for (int i = 0; i < 3; i++) {
            if (f1.get(i) == f2.get(i)) {
                strikeCnt++;
            }
            if (f1.get(i) != f2.get(i)) {
                //contains method 사용해서 ballCnt 계산
            }
        }*/
    }

    private static void separateInputNum(String userInput, List<Integer> userInputList) {
        int inputTemp = 0;
        for (int i = 0; i < 3; i++) {
            inputTemp = Integer.parseInt(userInput.substring(i, i + 1));
            userInputList.add(inputTemp);
        }
    }
}
