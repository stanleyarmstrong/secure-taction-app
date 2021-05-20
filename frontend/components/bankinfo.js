import React from 'react';
import {View, Image, StyleSheet} from 'react-native';
import {Text, IconButton} from 'react-native-paper';
import {useNavigation} from '@react-navigation/native';

const BankInfo = (props) => {
  const bank = props.bank ? props.bank : '';
  const budgetBalance = props.budget
    ? '$' + props.budget.toFixed(2)
    : 'Not Set';
  return (
    <View style={styles.row}>
      <View style={styles.cols}>
        <View style={styles.col1}>
          <Image
            source={{
              uri: bank,
            }}
            style={styles.image}
          />
        </View>
        <View style={styles.col2}>
          <Text> Balance: ${props.balance.toFixed(2)} </Text>
          <Text> Budget: {budgetBalance} </Text>
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  row: {
    height: 100,
  },
  cols: {
    padding: 20,
    flexDirection: 'row',
  },
  image: {
    height: 60,
    width: 60,
  },
  col1: {
    flex: 1,
  },
  col2: {
    flex: 2,
    justifyContent: 'space-evenly',
  },
});

export default BankInfo;
