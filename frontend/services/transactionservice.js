import axios from 'axios';
import {authToken} from './authToken';

export const getTransactions = (accountId) => {
  return axios({
    method: 'get',
    url: 'http://localhost:10180/plaid/transactions',
    data: {
      publicToken: accountId,
      userId: '81718C54-4B2C-4131-AD0F-D8726B0A9F4B',
      userName: 'demoUser',
    },
  })
    .then((data) => {
      return data;
    })
    .catch((error) => {
      console.error(error);
      return [];
    });
};
