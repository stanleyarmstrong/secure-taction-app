import axios from 'axios';
import {authToken} from './authToken';

export const getTransactions = (accountId) => {
  return axios({
    method: 'get',
    url: 'http://localhost:10180/plaid/transactions',
    data: {
      publicToken: accountId,
      userId: '5CC6D297-2415-4A36-8E61-79C011C3C9EF',
      userName: 'JohnnyBoy7',
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
