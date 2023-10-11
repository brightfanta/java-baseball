package baseball;


import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        List<Integer> computer = new ArrayList<>();
        List<Integer> userInputList = new ArrayList<>();

        String userInput = "";
        String restartInput = "1";
        int strikeCnt = 0;
        int ballCnt = 0;
        boolean inputCheck = true;

        System.out.println("숫자 야구 게임을 시작합니다.");

        while (restartInput.equals("1")) {

            computer.clear();
            //난수 생성
            generateRandomNum(computer);


            //게임 시작
            playBall(strikeCnt, userInput, userInputList, computer, ballCnt, inputCheck);

            //게임 종료 후 재시작 여부 확인
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            restartInput = Console.readLine();
        }

    }

    private static void generateRandomNum(List<Integer> computer) {
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
    }

    private static String getUserInput() {
        String userInput;
        System.out.print("숫자를 입력해주세요 : ");
        userInput = Console.readLine();
        System.out.println("userInput = " + userInput);
        return userInput;
    }

    private static void playBall(int strikeCnt, String userInput, List<Integer> userInputList, List<Integer> computer, int ballCnt, boolean inputCheck) {
        while (strikeCnt != 3) {

            //게임 파라미터 초기화
            strikeCnt = 0;
            ballCnt = 0;
            userInput = "";
            userInputList.clear();

            //사용자 입력
            userInput = getUserInput();
            inputCheck = userInputDuplicateCheck(userInput);

            //사용자 입력 에외 검증
            exceptionCheck(inputCheck);

            //숫자 분리
            separateInputNum(userInput, userInputList);

            //스트라이크 카운트
            strikeCnt = getStrikeCnt(computer, userInputList, strikeCnt);

            //볼카운트
            ballCnt = getBallCnt(computer, userInputList, ballCnt);

            //결과 산출
            printGameResult(strikeCnt, ballCnt);


            if (strikeCnt == 3) {
                System.out.println(strikeCnt + "개의 숫자를 모두 맞히셨습니다! 게임 종료");
                break;
            }
        }
    }

    private static void exceptionCheck (boolean inputCheck) throws IllegalArgumentException {
        if (!inputCheck) {
            throw new IllegalArgumentException("부적절한 이름입니다.");
        }
    }

    private static void separateInputNum(String userInput, List<Integer> userInputList) {
        int inputTemp = 0;
        if (userInput.length() == 3) {
            for (int i = 1; i <= 3; i++) {
                inputTemp = Integer.parseInt(userInput.substring(i - 1, i));
                userInputList.add(inputTemp);
            }
        }
        for (Integer i : userInputList) {
            System.out.println("i = " + i);
        }
    }

    private static int getBallCnt(List<Integer> computer, List<Integer> userInputList, int ballCnt) {
        for (int i = 0; i < 3; i++) {
            if(i == 0) continue;
            if (computer.get(0) == userInputList.get(i)) {
                ballCnt++;
            }
        }
        for (int i = 0; i < 3; i++) {
            if(i == 1) continue;
            if (computer.get(1) == userInputList.get(i)) {
                ballCnt++;
            }
        }
        for (int i = 0; i < 3; i++) {
            if(i == 2) continue;
            if (computer.get(2) == userInputList.get(i)) {
                ballCnt++;
            }
        }
        return ballCnt;
    }

    private static int getStrikeCnt(List<Integer> computer, List<Integer> userInputList, int strikeCnt) {

        for (int i = 0; i < 3; i++) {
            if (computer.get(i) == userInputList.get(i)) {
                strikeCnt++;
            }
        }
        return strikeCnt;
    }

    private static void printGameResult(int strikeCnt, int ballCnt) {
        if(strikeCnt != 0 && ballCnt != 0){
            System.out.println(ballCnt + "볼 " + strikeCnt + "스트라이크");
        } else if (strikeCnt != 0 && ballCnt == 0) {
            System.out.println(strikeCnt + "스트라이크");
        } else if (strikeCnt == 0 && ballCnt != 0) {
            System.out.println(ballCnt + "볼 ");
        } else if (strikeCnt == 0 && ballCnt == 0) System.out.println("낫싱");
    }

    private static boolean userInputDuplicateCheck(String userInput) {
        if (userInput.length() != 3) {
            return false;
        }
        if (userInput.charAt(0) == userInput.charAt(1)) {
            return false;
        }
        if (userInput.charAt(1) == userInput.charAt(2)) {
            return false;
        }
        if (userInput.charAt(0) == userInput.charAt(2)) {
            return false;
        }
        return true;
    }
}