import axios from 'axios';
import {authToken} from './authToken';

const baseurl = 'htttp://localhost:10180/budget/';
export const getBudget = (budgetId) => {
  return axios
    .get(
      'http://localhost:10180/budget/' +
        budgetId +
        '/5CC6D297-2415-4A36-8E61-79C011C3C9EF',
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
      console.log(success.data);
      return success.data;
    })
    .catch((error) => {
      console.error(error);
      return error;
    });
};
