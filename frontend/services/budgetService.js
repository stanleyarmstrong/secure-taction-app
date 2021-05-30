import axios from 'axios';
import {authToken} from './authToken';

const baseurl = 'htttp://localhost:10180/budget/';
export const getBudget = (budgetId) => {
  return axios
    .get(
      'http://localhost:10180/budget/' +
        budgetId +
        '/81718C54-4B2C-4131-AD0F-D8726B0A9F4B',
      {
        headers: {
          Authorization: authToken,
        },
      },
    )
    .then((success) => {
      return success.data;
    })
    .catch((error) => {
      console.error(error);
      return error;
    });
};

// doesn't work since Budget endpoint is broken
export const addBudget = (obj) => {
  return axios
    .post('http://localhost:10180/budget', obj, {
      headers: {
        Authorization: authToken,
      },
    })
    .then((success) => {
      console.log(success.status);
      return success.status;
    })
    .catch((error) => {
      console.error(error);
      return error;
    });
};
