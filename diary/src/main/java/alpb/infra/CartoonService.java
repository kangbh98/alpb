package alpb.infra;

import java.time.Duration;
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
        OpenAiService openAiService = new OpenAiService(OPENAI_KEY,Duration.ofSeconds(300));

        CreateImageRequest createImageRequest = CreateImageRequest.builder()
                .prompt(imageToRequest)
                .model("dall-e-3")
                .size("1024x1024")
                .n(1)
                .build();

        return openAiService.createImage(createImageRequest).getData().get(0).getUrl();


    }

    public String makePrompt(String comment) {
        String s = "하루기록{ 제주도는 너무 아름다워. 아침은 맛있게 먹었어. 그리고 점심에 관광지를 봤어. 오름을 보고 나서는 이쁜 카페를 갔고, 마지막으로 저녁도 맛났다 오늘 내 감상은: ";
        s += comment+"}"+"\n 위의 하루 기록을 포괄하는 cartoon을 만들어줘. 이미지 비율은 16:9로 해줘";
        return s;
    }

    
    public void createCartoon(Cartoon cartoon) { 
        cartoonRepository.save(cartoon);
    }

    public Cartoon getCartoon(Long userIdx , Date date) {
        return cartoonRepository.findByUserIdxAndDate(userIdx, date);
    }
}
