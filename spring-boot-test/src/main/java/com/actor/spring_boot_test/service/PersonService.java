package com.actor.spring_boot_test.service;

import com.actor.spring_boot_test.domain.BaseResult;
import com.actor.spring_boot_test.domain.Person;
import com.actor.spring_boot_test.repository.PersonRepository;
import com.actor.spring_boot_test.viewmodel.PersonDto;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description: 类的描述
 * date       : 2020/2/25 on 11:48
 */
@Service
@Slf4j
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    //增
    public BaseResult<Integer> insert(PersonDto personDto) {
        int column = personRepository.insert(personDto.toPerson());//返回影响的行数
//        return ResponseEntity.ok(person);
        return BaseResult.resultOk(column);
    }

    //删
    public BaseResult<Integer> deletePersonById(long id) {
//        return ResponseEntity.ok(animalRepository.deleteById(id));
        int column = personRepository.deleteById(id);//返回影响的行数
        return BaseResult.resultOk(column);
    }
//
//    public ResponseEntity<Integer> deleteAnimals(final Animal animal){
//        return ResponseEntity.ok(animalRepository.delete(packWrapper(animal, WrapperType.QUERY)));
//    }
//
//    public ResponseEntity<Integer> deleteAnimalsByIds(List<Integer> ids){
//        return ResponseEntity.ok(animalRepository.deleteBatchIds(ids));
//    }
//
//    public ResponseEntity<Integer> deleteAnimalsByMap(final Animal animal){
//        Map<String, Object> params = new HashMap<>();
//        if(Objects.nonNull(animal.getId())){
//            params.put("ID",animal.getId());
//        }
//        if(StringUtils.isNotEmpty(animal.getName())){
//            params.put("NAME", animal.getName());
//        }
//        if(Objects.nonNull(animal.getType())){
//            params.put("TYPE", animal.getType());
//        }
//        if(Objects.nonNull(animal.getSex())){
//            params.put("SEX", animal.getSex());
//        }
//        if (StringUtils.isNotEmpty(animal.getMaster())){
//            params.put("MASTER", animal.getMaster());
//        }
//        return ResponseEntity.ok(animalRepository.deleteByMap(params));
//    }

    //改
    public BaseResult<Integer> updatePerson(Person person) {
        Wrapper<Person> personWrapper = new UpdateWrapper<>();
        ((UpdateWrapper<Person>) personWrapper).eq("id", person.getId());
        int column = personRepository.update(person, personWrapper);//返回影响的行数
        return BaseResult.resultOk(column);
    }
//    public ResponseEntity<Integer> updateAnimals(final Animal animal, final Animal condition){
//        return ResponseEntity.ok(animalRepository.update(animal, packWrapper(condition, WrapperType.UPDATE)));
//    }
//

    //查
    public BaseResult<Person> getPersonById(long id) {
//        return ResponseEntity.ok(personRepository.selectById(id));
        return BaseResult.resultOk(personRepository.selectById(id));
    }

    //根据name模糊查询
    public BaseResult<IPage<Person>> getPersonsByName(long page, long size, String name) {
        IPage<Person> iPage = new Page<>(page, size, true);
        QueryWrapper<Person> wrapper = new QueryWrapper<>();
        wrapper.like("name", name);
        IPage<Person> personIPage = personRepository.selectPage(iPage, wrapper);
        return BaseResult.resultOk(personIPage);
    }
}
