package alpb.infra;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.service.OpenAiService;

import alpb.domain.Cartoon;
import alpb.domain.CartoonRepository;



@Service
public class CartoonService {
    @Value("${openai-key}")
    private String OPENAI_KEY;

    @Autowired
    CartoonRepository cartoonRepository;

    public String openAiImageUrl(String imageToRequest) {
        System.out.println(imageToRequest);
        System.out.println("OpenAI Key: " + OPENAI_KEY);
        OpenAiService service = new OpenAiService(OPENAI_KEY);
        CreateImageRequest build = CreateImageRequest.builder()
                .prompt(imageToRequest)
                .n(1)
                .size("512x512")
                .build();

        return service.createImage(build)
                .getData()
                .get(0)
                .getUrl();
    }

    public String makePrompt(String comment) {
        String s = "하루기록{ 제주도는 너무 아름다워. 아침은 제주도 바다를 바라보면서 회를 맛있게 먹었어. 그리고 점심에 오름을 봤어. 오름을 보고 나서는 이쁜 카페를 갔고, 마지막으로 저녁에 생선구이까지 ";
        s += comment+"}"+"\n 위의 하루 기록을 포괄하는 cartoon을 만들어줘. 이미지 비율은 16:9로 해줘";
        return s;
    }

    
    public void createCartoon(Cartoon cartoon) {
        cartoonRepository.createDiary(cartoon);
    }

    public Cartoon getCartoon(int userIdx , Date date) {
        return cartoonRepository.findByUserIdxAndDateI(userIdx, date);
    }
}
