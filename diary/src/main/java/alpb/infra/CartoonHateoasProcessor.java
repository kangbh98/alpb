package alpb.infra;

import alpb.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class CartoonHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Cartoon>> {

    @Override
    public EntityModel<Cartoon> process(EntityModel<Cartoon> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "//deletecartoon")
                .withRel("/deletecartoon")
        );

        return model;
    }
}
