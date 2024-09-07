
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import UserUserManager from "./components/listers/UserUserCards"
import UserUserDetail from "./components/listers/UserUserDetail"

import PlanPlanManager from "./components/listers/PlanPlanCards"
import PlanPlanDetail from "./components/listers/PlanPlanDetail"

import ReviewReviewManager from "./components/listers/ReviewReviewCards"
import ReviewReviewDetail from "./components/listers/ReviewReviewDetail"

import DiaryCartoonManager from "./components/listers/DiaryCartoonCards"
import DiaryCartoonDetail from "./components/listers/DiaryCartoonDetail"

import OrderOrderManager from "./components/listers/OrderOrderCards"
import OrderOrderDetail from "./components/listers/OrderOrderDetail"

import PaymentPaymentManager from "./components/listers/PaymentPaymentCards"
import PaymentPaymentDetail from "./components/listers/PaymentPaymentDetail"



export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/users/users',
                name: 'UserUserManager',
                component: UserUserManager
            },
            {
                path: '/users/users/:id',
                name: 'UserUserDetail',
                component: UserUserDetail
            },

            {
                path: '/plans/plans',
                name: 'PlanPlanManager',
                component: PlanPlanManager
            },
            {
                path: '/plans/plans/:id',
                name: 'PlanPlanDetail',
                component: PlanPlanDetail
            },

            {
                path: '/reviews/reviews',
                name: 'ReviewReviewManager',
                component: ReviewReviewManager
            },
            {
                path: '/reviews/reviews/:id',
                name: 'ReviewReviewDetail',
                component: ReviewReviewDetail
            },

            {
                path: '/diaries/cartoons',
                name: 'DiaryCartoonManager',
                component: DiaryCartoonManager
            },
            {
                path: '/diaries/cartoons/:id',
                name: 'DiaryCartoonDetail',
                component: DiaryCartoonDetail
            },

            {
                path: '/orders/orders',
                name: 'OrderOrderManager',
                component: OrderOrderManager
            },
            {
                path: '/orders/orders/:id',
                name: 'OrderOrderDetail',
                component: OrderOrderDetail
            },

            {
                path: '/payments/payments',
                name: 'PaymentPaymentManager',
                component: PaymentPaymentManager
            },
            {
                path: '/payments/payments/:id',
                name: 'PaymentPaymentDetail',
                component: PaymentPaymentDetail
            },




    ]
})
