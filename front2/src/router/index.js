import { createRouter, createWebHistory } from "vue-router";
import PresentationView from "../views/Presentation/PresentationView.vue";
import LoginView from "../views/User/LoginView.vue";
import SignUpView from "../views/User/SignUpView.vue";
import DestinationView from "../views/Destination/DestinationView.vue";
import PlanView from "../views/Plan/PlanView.vue";
import CheckCredit from "../views/Credit/CheckCredit.vue";
import OrderCredit from "../views/Credit/OrderCredit.vue";


const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/",
            name: "presentation",
            component: PresentationView,
        },
        {
            path: "/users/login",
            name: "login",
            component: LoginView,
        },
        {
            path: "/sign-up",
            name: "signup",
            component: SignUpView,
        },

        {
            path: "/pages/landing-pages/contact-us",
            name: "contactus",
            component: DestinationView,
        },
        {
            path: "/pages/Plan/PlanView",
            name: "plan",
            component: PlanView,
        },
        {
            path: "/check/credit",
            name: "checkcredit",
            component: CheckCredit,
        },
        {
            path: "/order/credit",
            name: "ordercredit",
            component: OrderCredit,
        },
    ],
});

export default router;
