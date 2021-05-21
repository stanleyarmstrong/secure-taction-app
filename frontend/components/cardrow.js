import React from 'react';
import {View, Image, StyleSheet} from 'react-native';
import {Text, Divider, IconButton} from 'react-native-paper';
import {useNavigation} from '@react-navigation/native';
import * as Progress from 'react-native-progress';

const CardRow = (props) => {
  const navigation = useNavigation();
  const progress =
    props.maxBudget && props.currentBudget
      ? (props.maxBudget - props.currentBudget) / props.currentBudget
      : 1;
  let set = false;
  const getColor = () => {
    // need to add code for greying out progress bar when
    if (progress === 1) {
      return '#C2C2C2';
    }
    set = true;
    if (progress >= 0.1) {
      return '#A8F388';
    } else if (progress < 0.1) {
      return '#FF7A72';
    }
  };
  const alert = props.alert > 0 ? props.alert : 0;
  const cancel = props.cancel > 0 ? props.cancel : 0;
  return (
    <View>
      <View style={styles.outer}>
        <View style={styles.col1}>
          <Image
            source={{
              uri: props.bank,
            }}
            style={styles.image}
          />
        </View>
        <View style={styles.col2}>
          <Text style={styles.alerts}> {props.name} </Text>
          <Progress.Bar
            color={getColor()}
            progress={progress}
            height={20}
            width={150}
            style={styles.progress}
          />
          <View>
            <Text style={styles.alerts}>
              Minimum Alert: ${alert.toFixed(2)}
            </Text>
            <Text style={styles.alerts}>Auto Cancel: ${cancel.toFixed(2)}</Text>
          </View>
        </View>
        <View style={styles.col3}>
          <IconButton
            icon={'arrow-right'}
            color={'#C2C2C2'}
            style={styles.icon}
            onPress={() => {
              console.log(props.maxBudget);
              console.log(props.currentBudget);
              navigation.push('budget', {
                accountName: props.name,
                bank: props.bank,
                alert: alert,
                cancel: cancel,
                maxBudget: props.maxBudget,
                currentBudget: props.currentBudget,
                set: set,
              });
            }}
            size={20}
          />
          <Text style={styles.balance}> ${props.balance.toFixed(2)} </Text>
          <IconButton
            icon={'delete-outline'}
            color={'#FF7A72'}
            style={styles.icon}
            size={20}
          />
        </View>
      </View>
      <Divider style={styles.divider} />
    </View>
  );
};

const styles = StyleSheet.create({
  outer: {
    flexDirection: 'row',
    alignContent: 'space-around',
  },
  col1: {
    flex: 5,
    alignSelf: 'center',
  },
  col2: {
    flex: 11,
    alignItems: 'center',
    justifyContent: 'space-evenly',
  },
  col3: {
    flex: 6.25,
    justifyContent: 'space-evenly',
    alignItems: 'center',
    marginBottom: 10,
  },
  image: {
    height: 60,
    width: 60,
  },
  balance: {
    fontSize: 17,
    marginBottom: 5,
  },
  icon: {
    marginLeft: 50,
    flexBasis: 20,
  },
  alerts: {
    fontSize: 12,
    textAlign: 'left',
  },
});
export default CardRow;
