import Badge from "../ArgonComp/Badge";
import BaseAlert from "../ArgonComp/BaseAlert";
import BaseButton from "../ArgonComp/BaseButton";
import BaseCheckbox from "../ArgonComp/BaseCheckbox";
import BaseInput from "../ArgonComp/BaseInput";
import BasePagination from "../ArgonComp/BasePagination";
import BaseProgress from "../ArgonComp/BaseProgress";
import BaseRadio from "../ArgonComp/BaseRadio";
import BaseSlider from "../ArgonComp/BaseSlider";
import BaseSwitch from "../ArgonComp/BaseSwitch";
import Card from "../ArgonComp/Card";
import Icon from "../ArgonComp/Icon";

export default {
  install(Vue) {
    Vue.component(Badge.name, Badge);
    Vue.component(BaseAlert.name, BaseAlert);
    Vue.component(BaseButton.name, BaseButton);
    Vue.component(BaseInput.name, BaseInput);
    Vue.component(BaseCheckbox.name, BaseCheckbox);
    Vue.component(BasePagination.name, BasePagination);
    Vue.component(BaseProgress.name, BaseProgress);
    Vue.component(BaseRadio.name, BaseRadio);
    Vue.component(BaseSlider.name, BaseSlider);
    Vue.component(BaseSwitch.name, BaseSwitch);
    Vue.component(Card.name, Card);
    Vue.component(Icon.name, Icon);
  }
};
