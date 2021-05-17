import React from 'react';
import {StyleSheet} from 'react-native';
import {Appbar} from 'react-native-paper';
import {View} from 'react-native';

const Navbar = ({navigation, previous, scene}) => {
  const {options} = scene.descriptor;
  const leftIcon = previous ? 'chevron-left' : 'account-circle-outline';
  const rightIcon = previous ? 'home-outline' : 'bell';
  const title = options.title ? options.title : 'Secure Taction';
  return (
    <View>
      <Appbar.Header style={styles.barColor}>
        <Appbar.Action
          icon={leftIcon}
          size={35}
          onPress={() => {
            if (leftIcon === 'chevron-left') {
              navigation.pop();
            } else {
              navigation.push('settings');
            }
          }}
        />
        <Appbar.Content title={title} titleStyle={styles.title} />
        <Appbar.Action
          icon={rightIcon}
          size={35}
          onPress={() => {
            if (rightIcon === 'bell') {
              // neeed to change to show notifications in a different change
              navigation.push('settings');
            } else {
              navigation.popToTop();
            }
          }}
        />
      </Appbar.Header>
    </View>
  );
};

const styles = StyleSheet.create({
  barColor: {
    backgroundColor: '#00A7E1',
  },
  title: {
    fontSize: 18,
  },
});

export default Navbar;
