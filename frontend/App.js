/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */
import 'react-native-gesture-handler';
import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createStackNavigator} from '@react-navigation/stack';
import {DefaultTheme, Provider as PaperProvider} from 'react-native-paper';
import Navbar from './components/navbar';
import HomeScreen from './screens/Homescreen';
import BudgetScreen from './screens/BudgetScreen';
import TransactionsScreen from './screens/TransactionsScreen';
import AddBudgetScreen from './screens/AddBudgetScreen';
import Settings from './screens/Settings';

const Stack = createStackNavigator();
const theme = {
  ...DefaultTheme,
  colors: {
    ...DefaultTheme.colors,
    primary: '#00A7E1',
    accent: '#ffffff',
  },
};

const App = () => {
  return (
    <PaperProvider theme={theme}>
      <NavigationContainer>
        <Stack.Navigator
          initialRouteName="home"
          screenOptions={{
            header: (props) => <Navbar {...props} />,
          }}>
          <Stack.Screen
            name="home"
            component={HomeScreen}
            options={{title: 'Good Morning, First Last'}}
          />
          <Stack.Screen
            name="budget"
            component={BudgetScreen}
            options={{title: "Card's Budget"}}
          />
          <Stack.Screen
            name="addbudget"
            component={AddBudgetScreen}
            options={{title: 'Add Budget'}}
          />
          <Stack.Screen
            name="transactions"
            component={TransactionsScreen}
            options={{title: 'Recent Activity'}}
          />
          <Stack.Screen
            name="settings"
            component={Settings}
            options={{title: 'Settings'}}
          />
        </Stack.Navigator>
      </NavigationContainer>
    </PaperProvider>
  );
};

export default App;
